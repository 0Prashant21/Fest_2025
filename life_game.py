import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation

# Grid size
SIZE = 50

# Initialize grid with random state
def initialize_grid():
    return np.random.choice([0, 1], SIZE*SIZE, p=[0.7, 0.3]).reshape(SIZE, SIZE)

# Calculate next generation
def update(frameNum, img, grid):
    newGrid = grid.copy()
    for i in range(SIZE):
        for j in range(SIZE):
            # Count living cells in 8 neighboring cells
            total = int((grid[i, (j-1)%SIZE] + grid[i, (j+1)%SIZE] +
                         grid[(i-1)%SIZE, j] + grid[(i+1)%SIZE, j] +
                         grid[(i-1)%SIZE, (j-1)%SIZE] + grid[(i-1)%SIZE, (j+1)%SIZE] +
                         grid[(i+1)%SIZE, (j-1)%SIZE] + grid[(i+1)%SIZE, (j+1)%SIZE]))

            # Conway's Game of Life rules
            if grid[i, j] == 1:
                if (total < 2) or (total > 3):
                    newGrid[i, j] = 0  # Dies from underpopulation or overpopulation
            else:
                if total == 3:
                    newGrid[i, j] = 1  # Birth

    img.set_data(newGrid)
    grid[:] = newGrid[:]
    return img,

# Main function
def main():
    grid = initialize_grid()

    fig, ax = plt.subplots()
    img = ax.imshow(grid, interpolation='nearest', cmap='binary')
    ax.set_title('Conway\'s Game of Life')
    ax.axis('off')

    ani = animation.FuncAnimation(fig, update, fargs=(img, grid),
                                  frames=200, interval=100, save_count=50)

    plt.show()

if __name__ == '__main__':
    main()
