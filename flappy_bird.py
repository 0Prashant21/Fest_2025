import pygame
import random
import sys

pygame.init()

WIDTH, HEIGHT = 400, 600
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Flappy Bird")

WHITE = (255, 255, 255)
BLUE = (0, 0, 255)
GREEN = (0, 255, 0)
FPS = 60
GRAVITY = 0.5
JUMP_STRENGTH = -10
PIPE_WIDTH = 70
PIPE_GAP = 150
PIPE_SPEED = 3

class Bird:
    def __init__(self):
        self.x = 100
        self.y = HEIGHT//2
        self.vel_y = 0
        self.width = 30
        self.height = 30

    def update(self):
        self.vel_y += GRAVITY
        self.y += self.vel_y

    def jump(self):
        self.vel_y = JUMP_STRENGTH

    def get_rect(self):
        return pygame.Rect(self.x, self.y, self.width, self.height)

class Pipe:
    def __init__(self):
        self.x = WIDTH
        self.height = random.randint(100, HEIGHT - 200)
        self.passed = False

    def update(self):
        self.x -= PIPE_SPEED

    def get_upper_rect(self):
        return pygame.Rect(self.x, 0, PIPE_WIDTH, self.height)

    def get_lower_rect(self):
        return pygame.Rect(self.x, self.height + PIPE_GAP, PIPE_WIDTH, HEIGHT - self.height - PIPE_GAP)

def main():
    clock = pygame.time.Clock()
    bird = Bird()
    pipes = [Pipe()]
    score = 0
    run = True

    while run:
        clock.tick(FPS)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    bird.jump()

        bird.update()

        for pipe in pipes:
            pipe.update()
            if pipe.x + PIPE_WIDTH < 0:
                pipes.remove(pipe)
            if pipe.x + PIPE_WIDTH < bird.x and not pipe.passed:
                score += 1
                pipe.passed = True
            if bird.get_rect().colliderect(pipe.get_upper_rect()) or bird.get_rect().colliderect(pipe.get_lower_rect()):
                run = False

        if pipes[-1].x < WIDTH - 200:
            pipes.append(Pipe())

        if bird.y > HEIGHT or bird.y < 0:
            run = False

        WIN.fill(WHITE)
        pygame.draw.rect(WIN, BLUE, bird.get_rect())
        for pipe in pipes:
            pygame.draw.rect(WIN, GREEN, pipe.get_upper_rect())
            pygame.draw.rect(WIN, GREEN, pipe.get_lower_rect())
        font = pygame.font.SysFont(None, 40)
        score_text = font.render(f"Score: {score}", True, (0,0,0))
        WIN.blit(score_text, (10,10))
        pygame.display.update()

    pygame.quit()
    sys.exit()

if __name__ == "__main__":
    main()
