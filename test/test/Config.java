package test;

/**
 * Configuration class for testing.
 * 
 * @author komalshah
 *
 */
public class Config {
  public static final String VALIDFILECONTENTS = "36 30 Doctor Lucky's Mansion\n"
      + "3 Doctor Lucky\n"
      + "Fortune the Cat\n"
      + "3\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n"
      + "28 12 35 19 Piazza\n"
      + "3\n"
      + "0 3 Revolver\n"
      + "1 3 Civil War Cannon\n"
      + "1 3 Cannon\n";
  
  public static final String INVALIDROWSIZE = "-36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n"
      + "2\n"
      + "0 3 Revolver\n"
      + "1 3 Civil War Cannon\n";
  
  public static final String INVALIDCOLUMNSIZE = "36 -30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n"
      + "2\n"
      + "0 3 Revolver\n"
      + "1 3 Civil War Cannon\n";
  
  public static final String INVALIDTOTALROOMS = "36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "-2\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n"
      + "2\n"
      + "0 3 Revolver\n"
      + "1 3 Civil War Cannon\n";
  
  public static final String INVALIDTOTALITEMS = "36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n"
      + "-2\n"
      + "0 3 Revolver\n"
      + "1 3 Civil War Cannon\n";
  
  public static final String INVALIDROOMS = "36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "0 3 Revolver\n"
      + "1 3 Civil War Cannon\n";
  
  public static final String ROOMALREADYEXISTS = "36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Green House\n"
      + "2\n"
      + "0 3 Revolver\n"
      + "1 3 Civil War Cannon\n";
  
  public static final String ITEMALREADYEXISTS = "36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n"
      + "2\n"
      + "0 3 Revolver\n"
      + "1 3 Revolver\n";
  
  public static final String ROOMNUMBERINVALIDFORITEM = "36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "28 26 35 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n"
      + "2\n"
      + "3 3 Revolver\n";
  
  public static final String INVALIDROOMSIZE = "36 30 Doctor Lucky's Mansion\n"
      + "50 Doctor Lucky\n"
      + "2\n"
      + "28 26 39 29 Green House\n"
      + "30 20 35 25 Hedge Maze\n";
}
