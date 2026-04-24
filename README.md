**Luke Belmer Shooter**

- **Overview**: A small Java Swing shooting game. Enter your name, press the Enter button to start, move the player image (Luke Belmer) with WASD, shoot bottles (Johnnie Walker) with Space, and try to shoot enemies before an enemy touches your player.

**Prerequisites**
- **Java**: JDK 17+ (Java 21 tested). Install and ensure `javac` and `java` are on your PATH.

**Build & Run**
- Compile (from project root):
```
javac -d bin src\*.java
```
- Run:
```
java -cp bin App
```
(If you use an IDE the workspace runs the `App` class directly.)

**Controls**
- **Move**: `W` `A` `S` `D` — moves the Luke Belmer image (clamped inside the window).
- **Shoot**: `Space` — fires a shot (2 second cooldown).
- **Start**: Click the `Enter` button after entering your name in the text field.

**Gameplay**
- Enemies spawn at the top at random X positions and move downwards.
- Every 2 spawned enemies the enemy speed increases (+1 speed step by default).
- Hitting an enemy with a shot increases the score by 1.
- If an enemy touches your Luke Belmer image, the game stops and a "Game Over" dialog shows your score; the UI resets to the name-entry screen so you can start again.

**Project Structure (key files)**
- **`src/App.java`**: Main app, UI, timers, spawn/collision logic, score, and game reset.
- **`src/GamePanel.java`**: Custom JPanel that draws active `Shot` and `enemy` objects.
- **`src/shot.java`**: `Shot` class: position, update(), draw(), and size getters.
- **`src/enemy.java`**: `enemy` class: position, configurable speed, update(), draw(), and size getters.
- **`src/images/`**: Folder for images used (e.g., `lukebelmer.jpg`, `johnnie walker.jpg`, `picture of andrew tate.jpg`). Make sure these image files exist at the paths used in `App.java`.

**Tuning / Configuration**
- Spawn interval: default 4000ms (change `new Timer(4000, ...)` in `App.java`).
- Shot cooldown: default 2000ms (change the `2000` check near the shoot key handler in `App.java`).
- Base enemy speed: default starts at `2` (see `enemy` constructor usage in `App.java`).

**Troubleshooting**
- If images don't show, verify paths in `App.java` and the working directory when running the app.
- If compilation fails, run `javac` on the `src` files and look at the first compile error — often due to case-sensitive filenames or missing imports.

**Next steps / Ideas**
- Add a visible cooldown indicator for shooting.
- Show on-screen difficulty / current enemy speed or high score.
- Add sounds for shooting and hits; add particle/explosion effect on hit.
- Add a restart button and a pause/resume control.

**License**
- Add a license file if you plan to publish or share the project.

Have me add any of the next steps (cooldown UI, restart button, smoothing enemy speed) and I can implement it for you.
