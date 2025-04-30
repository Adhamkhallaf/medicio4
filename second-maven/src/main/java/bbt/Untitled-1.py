
import pygame
import numpy as np

# Initialize Pygame
pygame.init()

# Constants
WIDTH, HEIGHT = 800, 600
GRID_SIZE = 20
BACKGROUND_COLOR = (30, 30, 30)
GRID_COLOR = (50, 50, 50)
LINE_COLOR = (255, 0, 0)
POINT_COLOR = (0, 255, 0)

# Screen setup
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Bresenham Line to Cursor")

# Grid helper
def draw_grid():
    for x in range(0, WIDTH, GRID_SIZE):
        pygame.draw.line(screen, GRID_COLOR, (x, 0), (x, HEIGHT))
    for y in range(0, HEIGHT, GRID_SIZE):
        pygame.draw.line(screen, GRID_COLOR, (0, y), (WIDTH, y))

# Bresenham's Line Algorithm
def bresenham(x0, y0, x1, y1):
    """Generate the points of a line using Bresenham's algorithm"""
    points = []
    dx = abs(x1 - x0)
    dy = -abs(y1 - y0)
    sx = 1 if x0 < x1 else -1
    sy = 1 if y0 < y1 else -1
    err = dx + dy

    while True:
        points.append((x0, y0))
        if x0 == x1 and y0 == y1:
            break
        e2 = 2 * err
        if e2 >= dy:
            err += dy
            x0 += sx
        if e2 <= dx:
            err += dx
            y0 += sy
    return points

# Convert pixel position to grid coordinates
def pixel_to_grid(pos):
    return pos[0] // GRID_SIZE, pos[1] // GRID_SIZE

# Convert grid coordinates to pixel position (center of cell)
def grid_to_pixel(cell):
    return cell[0] * GRID_SIZE + GRID_SIZE // 2, cell[1] * GRID_SIZE + GRID_SIZE // 2

# Fixed point in the grid
fixed_point = (10, 10)

# Main loop
running = True
clock = pygame.time.Clock()

while running:
    screen.fill(BACKGROUND_COLOR)
    draw_grid()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Get mouse position
    mouse_pos = pygame.mouse.get_pos()
    mouse_grid = pixel_to_grid(mouse_pos)

    # Draw line using Bresenham
    line_points = bresenham(fixed_point[0], fixed_point[1], mouse_grid[0], mouse_grid[1])
    for point in line_points:
        px, py = grid_to_pixel(point)
        pygame.draw.circle(screen, LINE_COLOR, (px, py), 3)

    # Draw fixed point
    fx, fy = grid_to_pixel(fixed_point)
    pygame.draw.circle(screen, POINT_COLOR, (fx, fy), 5)

    pygame.display.flip()
    clock.tick(60)

pygame.quit()
