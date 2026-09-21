# Purple wizard walking animation

60 transparent PNG frames, each 48 x 48 pixels. Each direction folder contains frames 01 through 15.

The game loads wizard-walk-sheet.png (720 x 192 pixels). Its four rows are down, left, right, up; each row contains 15 frames. GamePanel.loadWizardFrames() slices the sheet into images. update() selects a direction and advances a frame every four game updates. paintComponent() draws the chosen image at the player's position. Releasing movement resets to the first frame, keeping the last direction.

Run Main.java from the inner 2D Game project folder (the folder containing src and assets). Restart/rebuild an already running game to see changes. ticksPerFrame controls animation speed: larger values slow the animation. playerSpeed controls movement speed separately.

For a packaged JAR, include wizard-walk-sheet.png at the root of the classpath. The development loader also supports assets/wizard relative to the project working directory.

Artwork was generated using the built-in image generation tool. Generation prompt specification: a consistent pixel-art wizard wearing a purple robe and pointed hat, pale beard, brown boots, and gold belt; four complete walking cycles facing down, left, right, and up; transparent background; stable size and baseline; no props or text. Requested 15 columns by 4 rows. The output contained 16 columns; the first 15 per row were exported, aligned to a common baseline, and scaled with nearest-neighbor sampling to 48 x 48. The original is retained in source/generated-sheet.png. Generated poses can have minor differences between frames.

The subsequent background-removal generation produced an opaque checkerboard. The game uses the original source, whose genuine alpha transparency was verified, to preserve the wizard artwork.

Validation: Java compilation; all 60 files have 48 x 48 dimensions and transparent corners; four movement directions; full animation wrap; idle reset; offscreen rendering.
