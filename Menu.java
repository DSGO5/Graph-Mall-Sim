import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu 
{
    public static void main(String[] args) 
    {
        DSAGraph center = new DSAGraph(); 
        DSAHashTable shopTable = new DSAHashTable(1000); 
        boolean close = false;
        System.out.println("Welcome to the Shopping Centre!");
       
        while (!close) 
        {
            try
            {
                System.out.println("Please Select an Option From the Menu Below: \n" + 
                "1. Add a Shop to the Shopping Centre \n" +
                "2. Remove a Shop from the Shopping Centre \n" +
                "3. Add a Connection Between Two Shops \n" +
                "4. Remove a Connection Between Two Shops \n" +
                "5. Display the Adjacnecy List of all Shops \n" +
                "6. Display the Adjacency Matrix of all Shops \n" +
                "7. Update Shop Information\n" + 
                "8. Display the Shortest Path Between Two Shops \n" +
                "9. Instant Shop Search \n" + 
                "10. Display All of the shops in the Center \n" +
                "0. Exit\n" +
                "Please Enter Your Choice: "); 
                int option = inputInt(0, 10);

                switch (option) 
                {
                    case 1:
                        System.out.println("Please Enter Number of the Shop: ");
                        int shopNum = inputInt(0, 1000);

                        System.out.println("Please Enter the Name of the Shop: ");
                        String shopName = inputString();

                        System.out.println("Please Enter the Category of the Shop: ");
                        String category = inputString();

                        System.out.println("Please Enter the Location of the Shop: ");
                        String location = inputString();

                        System.out.println("Please Enter the Rating of the Shop(1-5): ");
                        int rating = inputInt(1, 5);

                        if (!center.hasShop(shopNum) && !center.shopWithLocation(location)) 
                        {
                            DSAGraphVertex v = new DSAGraphVertex(shopNum, shopName, category, location, rating);
                            center.addVertex(v);

                            shopTable.put((String)category, v);

                            System.out.println("Shop: " + center.getVertex(shopNum) + " Added Successfully!");
                        } 
                        else if(center.hasShop(shopNum) && center.shopWithLocation(location))
                        {
                            throw new IllegalArgumentException("Shop with the number: " + shopNum + " and Shop with Location: " + location + " already exists");
                        }
                        else if (center.hasShop(shopNum)) 
                        {
                            throw new IllegalArgumentException("Shop with the number: " + shopNum + " already exists");
                        } 
                        else if (center.shopWithLocation(location)) 
                        {
                            throw new IllegalArgumentException("Shop with Location: " + location + " already exists");
                        }
                        
                        break;

                    case 2:
                        System.out.println("Please enter the Number of the Shop you want to remove: ");
                        int delShop = inputInt(1, 1000); 
                        DSAGraphVertex deletedShop = center.getVertex(delShop);
                        if(deletedShop == null)
                        {
                            throw new IllegalArgumentException("Shop does not exist");
                        }
                        else
                        {
                            center.removeVertex(delShop);
                            shopTable.remove(deletedShop.getCategory(), deletedShop.getShopName());
                            System.out.println("SHOP: " + delShop + " has been removed");
                        }
                        break;

                    case 3:
                        System.out.println("Enter 'From' Shop Num: ");
                        int from  = inputInt(1, 1000);

                        System.out.println("Enter 'To' Shop Num: ");
                        int to = inputInt(1, 1000); 

                        System.out.println("Enter Edge Label: ");
                        String eLabel = inputString();
                        if(center.hasShop(from) && center.hasShop(to))
                        {
                            center.addEdge(from, to, eLabel);
                            System.out.println("Edge: " + center.getEdge(eLabel) + "has been added");
                        }
                        else if(!center.hasShop(from))
                        {
                            System.out.println("Shop: " + from + " does not exist");
                        }
                        else if(!center.hasShop(to))
                        {
                            System.out.println("Shop: " + to + " does not exist");
                        }
                        
                        break;
                    
                    case 4:
                        
                        System.out.println("Please enter the Label of the Edge you want to remove (E.g. AB): ");
                        String delEdge = inputString();
                        center.removeEdge(delEdge);
                        System.out.println("Connection has been removed");
                    
                        break; 

                    case 5:
                        System.out.println("\nGraph as Adjacency List:");
                        if(center.isEmpty())
                        {
                            throw new IllegalArgumentException("Center is empty");
                        }
                        else
                        {
                            center.displayAsList();
                        }
                        break;

                    case 6:
                        System.out.println("\nGraph as Adjacency Matrix:");
                        if(center.isEmpty())
                        {
                            throw new IllegalArgumentException("Center is empty");
                        }
                        else
                        {
                            center.displayAsMatrix();
                        }
                        break; 

                    case 7: 
                        System.out.println("Please enter the Num of the Shop you want to update: ");
                        int shop = inputInt(0, 1000);
                        System.out.println("Please Choose What to Update: \n" +
                                            "1. Shop Number \n" +
                                            "2. Shop Name \n" +
                                            "3. Shop Category \n" +
                                            "4. Shop Location \n" +
                                            "5. Shop Rating \n" +
                                            "Please Enter Your Choice: ");
                        int choice = inputInt(1, 5);
            
                        System.out.println("Please enter the new value: ");
                        String newVal = inputString();
                        String oldCat = center.getVertex(shop).getCategory();
                        if(center.hasShop(shop))
                        {
                            if(choice == 3)
                            {
                                shopTable.remove(oldCat, center.getVertex(shop).getShopName());
                                DSAGraphVertex newShop = center.updateVertexInformation(shop, choice, newVal);
                                shopTable.put(newShop.getCategory(), newShop);
                            }
                            else
                            {
                               center.updateVertexInformation(shop, choice, newVal);
                            }
                            System.out.println("Shop: " + shop + " Data has been updated");
                        }
                        else
                        {
                            throw new IllegalArgumentException("Shop does not exist");
                        }
                        break;
                    
                    case 8:
                        System.out.println("Please enter the Num of the Shop you want to start from: ");
                        int start = inputInt(0, 1000);
                        System.out.println("Please enter the Num of the Shop you want to end at: ");
                        int end = inputInt(0, 1000);
                        if(center.hasShop(start) && center.hasShop(end))
                        {
                            center.shortestPath(start, end);
                        }
                        else if(!center.hasShop(start) && !center.hasShop(end))
                        {
                            throw new IllegalArgumentException("Shop: " + start + " and " + end + " do not exist");
                        }
                        else if(!center.hasShop(start))
                        {
                            throw new IllegalArgumentException("Shop: " + start + " does not exist");
                        }
                        else if(!center.hasShop(end))
                        {
                            throw new IllegalArgumentException("Shop: " + end + " does not exist");
                        }
                        break;
                    
                    case 9: 

                        System.out.println("Please Enter the Category of the Shop you want to search: ");
                        String cat = inputString();
                        if(shopTable.hasKey(cat))
                        {
                            shopTable.sortAndPrint(cat);
                        }
                        else
                        {
                            System.out.println("No Shops Found for the Category: " + cat);
                        }
                        
                        break; 

                    case 10:
                        System.out.println("Shops in the Center: ");
                        System.out.println("************************************************************************");
                        center.displayAllVertexes();
                        System.out.println("************************************************************************");

                        break;
                    
                    case 0:
                        close = true;
                        System.out.println("Exiting...\n");
                        break;

                    default:
                        System.out.println("\nInvalid option\n");
                        break;
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nInvalid Input. Please Enter a Valid Input\n");
                
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("\n" + e.getMessage() + "\n");
            }
            catch (Exception e)
            {
                System.out.println("\nError: " + e.getMessage() + "\n");
            }
        }
    }

    private static int inputInt(int min, int max)
    {
        Scanner sc = new Scanner(System.in); 
        int input = sc.nextInt();
        if(input < min || input > max)
        {
            throw new IllegalArgumentException("\nInvalid Input. Please Enter an Integer between " + min + " and " + max + "\n");
        }
        
        return input;
    }
    
    private static String inputString()
    {
        Scanner sc = new Scanner(System.in); 
        String input = sc.nextLine();
        if(input.trim().isEmpty())
        {
            throw new IllegalArgumentException("\nInvalid Input. Please Enter a Valid Input\n");
        }
        
        return input;
    }
}

