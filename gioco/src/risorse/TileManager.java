package risorse;

import gioco.Scena;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    Scena scena;
    Tile[] tile;
    public TileManager(Scena scena) {
        this.scena = scena;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/SpaceInvaders_Background.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/imageS/SpaceInvaders_BackgroundBuildings.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/SpaceInvaders_BackgroundFloor.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                g2.drawImage(tile[0].image, i * 64, j * 64, 64, 64, null);
            }
        }

        for(int i=0; i<9; i++){
            g2.drawImage(tile[1].image, i * 64,  512, 64, 64, null);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 9; j < 14; j++) {
                g2.drawImage(tile[2].image, i * 64, j * 64, 64, 64, null);
            }
        }
    }
}