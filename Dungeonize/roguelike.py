

import curses
import random
import json
from collections import deque, namedtuple

WIDTH = 80
HEIGHT = 24
MAX_ROOMS = 8
ROOM_MIN = 4
ROOM_MAX = 10
FOV_RADIUS = 8
SAVE_FILE = "rogue_save.json"

Point = namedtuple("Point", ["x", "y"])

# Tile definitions
WALL = "#"
FLOOR = "."
PLAYER = "@"
ENEMY = "e"
POTION = "!"
STAIRS = ">"

def clamp(v, a, b):
    return max(a, min(b, v))

class RectRoom:
    def __init__(self, x, y, w, h):
        self.x1 = x
        self.y1 = y
        self.x2 = x + w
        self.y2 = y + h

    def center(self):
        return Point((self.x1 + self.x2)//2, (self.y1 + self.y2)//2)

    def intersects(self, other):
        return (self.x1 <= other.x2 and self.x2 >= other.x1 and
                self.y1 <= other.y2 and self.y2 >= other.y1)

class Entity:
    def __init__(self, x, y, ch, hp=1, name="entity"):
        self.x = x
        self.y = y
        self.ch = ch
        self.hp = hp
        self.name = name
    def pos(self):
        return Point(self.x, self.y)

class Game:
    def __init__(self, stdscr):
        self.stdscr = stdscr
        self.map = [[WALL for _ in range(WIDTH)] for __ in range(HEIGHT)]
        self.rooms = []
        self.player = None
        self.entities = []
        self.items = {}
        self.msgs = deque(maxlen=6)
        self.turn = 0
        self.make_map()
        self.running = True

    def log(self, s):
        self.msgs.appendleft(s)

    def make_map(self):
        self.rooms = []
        for _ in range(MAX_ROOMS):
            w = random.randint(ROOM_MIN, ROOM_MAX)
            h = random.randint(ROOM_MIN, ROOM_MAX)
            x = random.randint(1, WIDTH - w - 2)
            y = random.randint(1, HEIGHT - h - 2)
            newr = RectRoom(x, y, w, h)
            if any(newr.intersects(r) for r in self.rooms):
                continue
            self.create_room(newr)
            if self.rooms:
                (prev_x, prev_y) = self.rooms[-1].center()
                (new_x, new_y) = newr.center()
                if random.random() < 0.5:
                    self.create_h_corridor(prev_x, new_x, prev_y)
                    self.create_v_corridor(prev_y, new_y, new_x)
                else:
                    self.create_v_corridor(prev_y, new_y, prev_x)
                    self.create_h_corridor(prev_x, new_x, new_y)
            self.rooms.append(newr)

        start = self.rooms[0].center()
        self.player = Entity(start.x, start.y, PLAYER, hp=10, name="You")
        last = self.rooms[-1].center()
        self.map[last.y][last.x] = STAIRS

        for r in self.rooms[1:]:
            for _ in range(random.randint(0, 2)):
                x = random.randint(r.x1+1, r.x2-1)
                y = random.randint(r.y1+1, r.y2-1)
                if not self.is_blocked(x, y):
                    if random.random() < 0.6:
                        e = Entity(x, y, ENEMY, hp=random.randint(2,5), name="Goblin")
                        self.entities.append(e)
                    else:
                        self.items[(x, y)] = POTION

    def create_room(self, room):
        for y in range(room.y1, room.y2+1):
            for x in range(room.x1, room.x2+1):
                if x == room.x1 or y == room.y1 or x == room.x2 or y == room.y2:
                    self.map[y][x] = WALL
                else:
                    self.map[y][x] = FLOOR

    def create_h_corridor(self, x1, x2, y):
        for x in range(min(x1,x2), max(x1,x2)+1):
            self.map[y][x] = FLOOR

    def create_v_corridor(self, y1, y2, x):
        for y in range(min(y1,y2), max(y1,y2)+1):
            self.map[y][x] = FLOOR

    def is_blocked(self, x, y):
        if self.map[y][x] == WALL:
            return True
        if any(e.x == x and e.y == y for e in self.entities):
            return True
        if self.player.x == x and self.player.y == y:
            return True
        return False

    def handle_input(self, c):
        dx = dy = 0
        if c in (curses.KEY_UP, ord('k')): dy = -1
        elif c in (curses.KEY_DOWN, ord('j')): dy = 1
        elif c in (curses.KEY_LEFT, ord('h')): dx = -1
        elif c in (curses.KEY_RIGHT, ord('l')): dx = 1
        elif c == ord('g'):
            self.pick_up()
            return
        elif c == ord('s'):
            self.save()
            self.log("Game saved.")
            return
        elif c == ord('q'):
            self.running = False
            return
        else:
            return

        self.player_move(dx, dy)
        self.turn += 1
        self.update_enemies()

    def player_move(self, dx, dy):
        nx = clamp(self.player.x + dx, 1, WIDTH-2)
        ny = clamp(self.player.y + dy, 1, HEIGHT-2)
        target = next((e for e in self.entities if e.x==nx and e.y==ny), None)
        if target:
            dmg = random.randint(1,4)
            target.hp -= dmg
            self.log(f"You hit {target.name} for {dmg}. ({target.hp} hp)")
            if target.hp <= 0:
                self.log(f"{target.name} dies!")
                self.entities.remove(target)
            return
        if self.map[ny][nx] != WALL:
            self.player.x, self.player.y = nx, ny
            if (nx,ny) in self.items:
                self.log("You see something here (press 'g' to pick up).")
            if self.map[ny][nx] == STAIRS:
                self.log("You descend the stairs... deeper dungeon!")
                self.deepen()

    def pick_up(self):
        pos = (self.player.x, self.player.y)
        if pos in self.items:
            item = self.items.pop(pos)
            if item == POTION:
                heal = random.randint(3,6)
                self.player.hp = min(self.player.hp + heal, 20)
                self.log(f"You drink a potion and heal {heal}. HP: {self.player.hp}")
        else:
            self.log("Nothing here to pick up.")

    def deepen(self):
        self.map = [[WALL for _ in range(WIDTH)] for __ in range(HEIGHT)]
        self.entities = []
        self.items = {}
        self.make_map()
        self.log("A new level is generated!")

    def update_enemies(self):
        for e in list(self.entities):
            if abs(e.x - self.player.x) <= 1 and abs(e.y - self.player.y) <= 1:
                dmg = random.randint(1,3)
                self.player.hp -= dmg
                self.log(f"{e.name} hits you for {dmg}! HP: {self.player.hp}")
                if self.player.hp <= 0:
                    self.log("You died! Press 'q' to quit.")
                    self.running = False
                continue
            if abs(e.x - self.player.x) + abs(e.y - self.player.y) <= 6:
                dx = 1 if self.player.x > e.x else -1 if self.player.x < e.x else 0
                dy = 1 if self.player.y > e.y else -1 if self.player.y < e.y else 0
                nx, ny = e.x + dx, e.y + dy
                if not self.is_blocked(nx, ny):
                    e.x, e.y = nx, ny
            else:
                if random.random() < 0.4:
                    dx, dy = random.choice([(1,0),(-1,0),(0,1),(0,-1),(0,0)])
                    nx, ny = e.x+dx, e.y+dy
                    if not self.is_blocked(nx, ny) and self.map[ny][nx] != WALL:
                        e.x, e.y = nx, ny

    def compute_fov(self):
        visible = set()
        px, py = self.player.x, self.player.y
        for y in range(max(0, py-FOV_RADIUS), min(HEIGHT, py+FOV_RADIUS+1)):
            for x in range(max(0, px-FOV_RADIUS), min(WIDTH, px+FOV_RADIUS+1)):
                if (x-px)**2 + (y-py)**2 <= FOV_RADIUS**2:
                    if self._los(px, py, x, y):
                        visible.add((x,y))
        return visible

    def _los(self, x1, y1, x2, y2):
        dx = abs(x2-x1); sx = 1 if x1<x2 else -1
        dy = -abs(y2-y1); sy = 1 if y1<y2 else -1
        err = dx + dy
        cx, cy = x1, y1
        while True:
            if self.map[cy][cx] == WALL and not (cx==x1 and cy==y1):
                return False
            if cx==x2 and cy==y2:
                return True
            e2 = 2*err
            if e2 >= dy:
                err += dy; cx += sx
            if e2 <= dx:
                err += dx; cy += sy

    def draw(self):
        self.stdscr.clear()
        visible = self.compute_fov()
        for y in range(HEIGHT):
            for x in range(WIDTH):
                if (x,y) in visible:
                    ch = self.map[y][x]
                    if (x,y) in self.items:
                        ch = self.items[(x,y)]
                    entity_here = next((e for e in self.entities if e.x==x and e.y==y), None)
                    if entity_here:
                        ch = entity_here.ch
                    if self.player.x==x and self.player.y==y:
                        ch = self.player.ch
                    self.stdscr.addch(y,x,ch)
                else:
                    self.stdscr.addch(y,x,' ')
        for i, msg in enumerate(self.msgs):
            self.stdscr.addstr(i, 0, msg[:WIDTH])
        self.stdscr.addstr(HEIGHT-1, 0, f"HP: {self.player.hp}  Turn: {self.turn}")
        self.stdscr.refresh()

    def save(self):
        data = {
            "player": {"x": self.player.x, "y": self.player.y, "hp": self.player.hp},
            "entities": [{"x":e.x,"y":e.y,"ch":e.ch,"hp":e.hp,"name":e.name} for e in self.entities],
            "items": [{"x":x,"y":y,"ch":c} for (x,y),c in self.items.items()],
            "map": self.map,
        }
        with open(SAVE_FILE,"w") as f:
            json.dump(data,f)

    def load(self):
        try:
            with open(SAVE_FILE,"r") as f:
                data = json.load(f)
            self.player.x = data["player"]["x"]
            self.player.y = data["player"]["y"]
            self.player.hp = data["player"]["hp"]
            self.entities = [Entity(e["x"],e["y"],e["ch"],e["hp"],e["name"]) for e in data["entities"]]
            self.items = {(i["x"],i["y"]):i["ch"] for i in data["items"]}
            self.map = data["map"]
            self.log("Game loaded!")
        except FileNotFoundError:
            self.log("No save file found.")

def main(stdscr):
    curses.curs_set(0)
    stdscr.nodelay(False)
    g = Game(stdscr)
    g.load()
    while g.running:
        g.draw()
        c = stdscr.getch()
        g.handle_input(c)

if __name__ == "__main__":
    curses.wrapper(main)
