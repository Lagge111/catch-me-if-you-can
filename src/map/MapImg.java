package map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.Image;

/**
 * Represents the "static" images used in the game, i.e. images that don't move,
 * e.g. houses, roads etc.
 */
public class MapImg {

   private Image house1;
   private Image house2;
   private Image house3;
   private Image bank1;
   private Image bank2;
   private Image road_cross;
   private Image road_horizontal;
   private Image road_vertical;
   private Image coin;
   private Image powerup;
   private Image sand;
   private Image saloon1;
   private Image road_downleft;
   private Image road_leftup;
   private Image road_rightdown;
   private Image road_tdown;
   private Image road_tleft;
   private Image road_tright;
   private Image road_tup;
   private Image road_upright;
   private Image cactus;
   private Image sheriffStation;
   private Image hotel;
   private Image[] bank = new Image[2];
   private ArrayList<Image> houses = new ArrayList<Image>();
   private HashMap<Integer, Image> roads = new HashMap<Integer, Image>();
   private ArrayList<Image> wildWest = new ArrayList<Image>();

   public MapImg() {

      try {
         house1 = new Image(new FileInputStream("assets/house1.jpg"));
         house2 = new Image(new FileInputStream("assets/house2.jpg"));
         house3 = new Image(new FileInputStream("assets/house3.jpg"));
         bank1 = new Image(new FileInputStream("assets/bank1.jpg"));
         bank2 = new Image(new FileInputStream("assets/bank2.jpg"));
         road_cross = new Image(new FileInputStream("assets/road_c.jpg"));
         road_horizontal = new Image(new FileInputStream("assets/road_h.jpg"));
         road_vertical = new Image(new FileInputStream("assets/road_v.jpg"));
         coin = new Image(new FileInputStream("assets/coin.png"));
         powerup = new Image(new FileInputStream("assets/powerup.png"));
         sand = new Image(new FileInputStream("assets/sand.jpg"));
         saloon1 = new Image(new FileInputStream("assets/saloon1.jpg"));
         road_downleft = new Image(new FileInputStream("assets/road_downleft.jpg"));
         road_leftup = new Image(new FileInputStream("assets/road_leftup.jpg"));
         road_rightdown = new Image(new FileInputStream("assets/road_rightdown.jpg"));
         road_tdown = new Image(new FileInputStream("assets/road_tdown.jpg"));
         road_tleft = new Image(new FileInputStream("assets/road_tleft.jpg"));
         road_tright = new Image(new FileInputStream("assets/road_tright.jpg"));
         road_tup = new Image(new FileInputStream("assets/road_tup.jpg"));
         road_upright = new Image(new FileInputStream("assets/road_upright.jpg"));
         cactus = new Image(new FileInputStream("assets/cactus.png"));
         sheriffStation = new Image(new FileInputStream("assets/sheriffstation.jpg"));
         hotel = new Image(new FileInputStream("assets/hotel.jpg"));
      } catch (FileNotFoundException e) {
         System.out.println("Couldn't find house image");
         e.printStackTrace();
      }

      houses.add(house1);
      houses.add(house2);
      houses.add(house3);

      bank[0] = bank1;
      bank[1] = bank2;

      roads.put(1, road_cross);
      roads.put(2, road_horizontal);
      roads.put(3, road_vertical);
      roads.put(4, road_downleft);
      roads.put(5, road_leftup);
      roads.put(6, road_rightdown);
      roads.put(7, road_tdown);
      roads.put(8, road_tleft);
      roads.put(9, road_tright);
      roads.put(10, road_tup);
      roads.put(11, road_upright);

      wildWest.add(cactus);
      wildWest.add(saloon1);
      wildWest.add(sheriffStation);
      wildWest.add(hotel);
   }

   public Image printHouses(int i) {
      return houses.get(i);
   }

   public Image printRoads(int i) {
      return roads.get(i);
   }
   
   public Image printWildWest(int i) {
      return wildWest.get(i);
   }
   
   public Image printSand() {
      return sand;
   }

   public ArrayList<Image> getHouses() {
      return houses;
   }

   public HashMap<Integer, Image> getRoads() {
      return roads;
   }
   
   public ArrayList<Image> getWildWest() {
      return wildWest;
   }

   public Image getCoin() {
      return coin;
   }

   public Image getPowerup() {
      return powerup;
   }

   public Image[] getBank() {
      return bank;
   }


}
