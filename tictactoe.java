import java.util.Scanner;
public class tictactoe {
    
    public static void main(String[] args) {
        
        int[][][] Gameboard = new int[4][4][4];
        boolean notover = true;
        boolean userforkdetected;
        boolean userlinedetected;
        boolean compforkdetected;
        while(notover){
            
           int[] coordinates = playermove(Gameboard);
        Gameboard[coordinates[0]][coordinates[1]][coordinates[2]] = 5;
        notover = Computerbrain.detectGameover(Gameboard);
        if (notover == false){
            Interface.display(Gameboard);
            break;
        }
        if (Computerbrain.detectlineoffense(Gameboard)){
            int[] coordinates2 = Computerbrain.playline(Gameboard);
            Gameboard[coordinates2[0]][coordinates2[1]][coordinates2[2]] = 1;
            notover = Computerbrain.detectGameover(Gameboard);
            if (notover == false){
                Interface.display(Gameboard);
                break;
            }
        }
        
        int[] c;
        userlinedetected = Computerbrain.detectlinedefense(Gameboard);
        if (userlinedetected){
        c = Computerbrain.blockuser1(Gameboard);
        Gameboard[c[0]][c[1]][c[2]] = 1;
        Interface.display(Gameboard);
        
        }
         userforkdetected = Computerbrain.detectForkdefense(Gameboard);
         if (userforkdetected){
             c = Computerbrain.blockuser2(Gameboard);
             Gameboard[c[0]][c[1]][c[2]] = 1;
             
         }
         compforkdetected = Computerbrain.detectforkoffense(Gameboard);
         if (compforkdetected){
             c = Computerbrain.playfork(Gameboard);
             Gameboard[c[0]][c[1]][c[2]] = 1;
         } 
         if (userforkdetected == false & userlinedetected == false){ 
         c = Computerbrain.playliveline(Gameboard);
         Gameboard[c[0]][c[1]][c[2]] = 1;
          
        }
         Interface.display(Gameboard);
         
        }
        
        
    }
public static int[] playermove(int[][][] j) {
    int[] coordinates = new int[3];
    System.out.println("Enter a level, row and column: in that order.");
    Scanner input = new Scanner(System.in);
    coordinates[0] = input.nextInt();
    coordinates[1] = input.nextInt();
    coordinates[2] = input.nextInt();
    return coordinates;
}

}
class Interface {
   public static void display(int[][][] b){
       
   
        
    
    String Gameboard2 [][][] = new String [4][4][4];
    
    int i,j,k;
        for (i = 0; i <= 3; i++) {
            
            for (j = 0; j <= 3; j++){
                for(k = 0; k <= 3; k++){
                    int value = b [i][j][k];
                    switch (value) {
                        case 0 : Gameboard2 [i][j][k] = "_    ";
                            break;
                        case 1 : Gameboard2 [i][j][k] = "O    ";
                            break;
                        case 5 : Gameboard2 [i][j][k] = "X    ";
                            break;
                        default : Gameboard2 [i][j][k] = "E    ";
                    }
                }
            }
        }
        for (i = 0; i <= 3; i++) {
            System.out.println();
            
            for (j = 0; j <= 3; j++){
                if (j == 0){
                System.out.print("      ");
            }
            if (j == 1){
                System.out.print("    ");
            }
            if (j == 2){
                System.out.print("  ");
            }
                System.out.print(Gameboard2[i][j][0] + Gameboard2[i][j][1]);
                System.out.print(Gameboard2[i][j][2] + Gameboard2[i][j][3]);
                System.out.println();
            }
        }
    
   }
     
    
}
class Computerbrain {
    
       public static final int[][][] lines = {
	{{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0   rows in each lev
	{{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1     
	{{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2     
	{{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3     
	{{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0     
	{{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1     
	{{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2     
	{{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3     
	{{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0     
	{{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1     
	{{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2       
	{{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3     
	{{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0     
	{{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1 
	{{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2       
	{{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3           
	{{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0   cols in each lev
	{{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1    
	{{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2    
	{{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3    
	{{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0     
	{{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1    
	{{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2    
	{{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3    
	{{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0     
	{{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1    
	{{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2    
	{{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3    
	{{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0     
	{{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
	{{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
	{{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
        {{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //cols in vert plane in front
        {{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
        {{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
        {{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
        {{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols in vert plane one back
        {{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
        {{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
        {{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
        {{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane two back
        {{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
        {{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
        {{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
        {{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
        {{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
        {{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
        {{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
        {{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
	{{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
        {{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
        {{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
        {{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
        {{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
        {{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
        {{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
        {{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane in front
        {{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
        {{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane one back
        {{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
        {{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane two back
        {{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
        {{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane in rear
        {{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
        {{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice      
        {{3,0,0},{2,1,0},{1,2,0},{0,3,0}},        
        {{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
        {{3,0,1},{2,1,1},{1,2,1},{0,3,1}},        
        {{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right      
        {{3,0,2},{2,1,2},{1,2,2},{0,3,2}},        
        {{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice      
        {{3,0,3},{2,1,3},{1,2,3},{0,3,3}},        
        {{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
        {{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
        {{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
        {{3,3,0},{2,2,1},{1,1,2},{0,0,3}}        
    };
public static boolean detectlinedefense(int[][][] j){
    boolean linedetected = true;
    int i,l,k;
    int[] coordinates = new int[3];
    int[][] pointsfilled = new int[76][4];
    for (i = 0;i < 76; i++){
        for (l = 0;l < 4; l++){
         int val =  j [lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         pointsfilled[i][l] = val;  
         
               
      }
    }
    for (i = 0;i < 76; i++){
        int sum = pointsfilled[i][0]+pointsfilled[i][1]+pointsfilled[i][2]+pointsfilled[i][3];
            if(sum == 15){
                linedetected = true;
                break;
            } else linedetected = false;
    }
    return linedetected;
}       
public static int[] blockuser1(int[][][] j){
    int i,l,k;
    int[] coordinates = new int[3];
    int[][] pointsfilled = new int[76][4];
    for (i = 0;i < 76; i++){
        for (l = 0;l < 4; l++){
         int val =  j [lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         pointsfilled[i][l] = val;  
         
               
      }
    }
    for (i = 0;i < 76; i++){
        int sum = pointsfilled[i][0]+pointsfilled[i][1]+pointsfilled[i][2]+pointsfilled[i][3];
            if(sum == 15){
                for(int y = 0;y < 4; y++){
                   if (pointsfilled[i][y] == 0){
                       
                       coordinates[0] = lines[i][y][0];
                       coordinates[1] = lines[i][y][1];
                       coordinates[2] = lines[i][y][2];
                       
                   } 
                   
                }
            }
                }
    
    return coordinates;
}
public static boolean detectGameover(int[][][] j){
    int[][] linepoints = new int[76][4];
    boolean notover = false;
    int i,l;
    for (i = 0;i < 76; i++){
        
        for (l = 0;l < 4; l++){
         int val =  j [lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         linepoints[i][l] = val;  
         
    
       
    
    
}
}
    for(i = 0; i < 76; i++){
    int sum = linepoints[i][0] + linepoints[i][1] + linepoints[i][2] + linepoints[i][3];
        if (sum == 20 || sum == 4){
            
            notover = false;
            break;
        }else notover = true;
               
      }return notover;
}
public static boolean detectForkdefense(int[][][] j){
    int[][][] temparray = j;
    boolean forkdetected = false;
    int[][] linepointstest = new int[76][4];
    int[][] linepoints = new int[76][4];
    int i,l,k;
    int[] usefullines = new int[76];
    for (i = 0;i < 76; i++){
        for(l = 0; l < 4; l++){
            int val =   temparray[lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         linepoints[i][l] = val;
        }
        
    }
    int sum;
for(i = 0; i < 76; i++){
     sum = linepoints[i][0]+linepoints[i][1]+linepoints[i][2]+linepoints[i][3];
    if(sum == 10){
        usefullines[i] = 1;
    }
}
int x, y,a, b, c;
int count = 0;
for(x = 0; x < 76; x++){
    if(usefullines[x] == 1){
        for(y = 0; y < 4; y++){
            if(linepoints[x][y] == 0){
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 5;
                for (b = 0;b < 76; b++){
        for(c = 0; c < 4; c++){
            int val =   temparray[lines[b][c][0]][lines[b][c][1]][lines[b][c][2]];
         linepointstest[b][c] = val;
        }
        
    }
                
                
                for(a = 0; a < 76; a++){
               sum = linepointstest[a][0]+linepointstest[a][1]+linepointstest[a][2]+linepointstest[a][3];
                   
               if (sum == 15){
                        count++;
                        
                    }
                    
                }
                if(count > 1){
                        forkdetected = true;
                    }
                
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 0;
                count = 0;
            } 
            }
        }
    
}

System.out.println(count);
return forkdetected;
    }
public static int[] blockuser2(int[][][] j){
    int[][][] temparray = j;
    int[] blockfork = new int[3];
    boolean forkdetected = false;
    int[][] linepointstest = new int[76][4];
    int[][] linepoints = new int[76][4];
    int i,l,k;
    int[] usefullines = new int[76];
    for (i = 0;i < 76; i++){
        for(l = 0; l < 4; l++){
            int val =   temparray[lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         linepoints[i][l] = val;
        }
        
    }
    int sum;
for(i = 0; i < 76; i++){
     sum = linepoints[i][0]+linepoints[i][1]+linepoints[i][2]+linepoints[i][3];
    if(sum == 10){
        usefullines[i] = 1;
    }
}
int x, y,a, b, c;
int count = 0;
for(x = 0; x < 76; x++){
    if(usefullines[x] == 1){
        for(y = 0; y < 4; y++){
            if(linepoints[x][y] == 0){
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 5;
                for (b = 0;b < 76; b++){
        for(c = 0; c < 4; c++){
            int val =   temparray[lines[b][c][0]][lines[b][c][1]][lines[b][c][2]];
         linepointstest[b][c] = val;
        }
        
    }
                
                
                for(a = 0; a < 76; a++){
               sum = linepointstest[a][0]+linepointstest[a][1]+linepointstest[a][2]+linepointstest[a][3];
                   
               if (sum == 15){
                        count++;
                        
                    }
                    
                }
                if(count > 1){
                        forkdetected = true;
                        
                        blockfork[0] = lines[x][y][0];
                        blockfork[1] = lines[x][y][1];
                        blockfork[2] = lines[x][y][2];
                    }
                
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 0;
                count = 0;
            } 
            }
        }
    
}

System.out.println(count);
return blockfork;
    
}
public static boolean detectlineoffense(int[][][] j){
     boolean linedetected = true;
    int i,l,k;
    int[] coordinates = new int[3];
    int[][] pointsfilled = new int[76][4];
    for (i = 0;i < 76; i++){
        for (l = 0;l < 4; l++){
         int val =  j [lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         pointsfilled[i][l] = val;  
         
               
      }
    }
    for (i = 0;i < 76; i++){
        int sum = pointsfilled[i][0]+pointsfilled[i][1]+pointsfilled[i][2]+pointsfilled[i][3];
            if(sum == 3){
                linedetected = true;
                
                break;
            } else linedetected = false;
    }
    return linedetected;
}
public static int[] playline(int[][][] j){
    int[] c = new int[3];
    boolean linedetected = true;
    int i,l,k;
    int[] coordinates = new int[3];
    int[][] pointsfilled = new int[76][4];
    for (i = 0;i < 76; i++){
        for (l = 0;l < 4; l++){
         int val =  j [lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         pointsfilled[i][l] = val;  
         
               
      }
    }
    for (i = 0;i < 76; i++){
        int sum = pointsfilled[i][0]+pointsfilled[i][1]+pointsfilled[i][2]+pointsfilled[i][3];
            if(sum == 3){
                linedetected = true;
                for(int y = 0; y < 4; y++){
                    if (pointsfilled[i][y] == 0){
                        
                        c[0] = lines[i][y][0];
                        c[1] = lines[i][y][1];
                        c[2] = lines[i][y][2];
                    }
                    
                    
                }
                break;
            } else linedetected = false;
    }
    return c;
}
public static boolean detectforkoffense(int[][][] j){
     int[][][] temparray = j;
    int[] blockfork = new int[3];
    boolean forkdetected = false;
    int[][] linepointstest = new int[76][4];
    int[][] linepoints = new int[76][4];
    int i,l,k;
    int[] usefullines = new int[76];
    for (i = 0;i < 76; i++){
        for(l = 0; l < 4; l++){
            int val =   temparray[lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         linepoints[i][l] = val;
        }
        
    }
    int sum;
for(i = 0; i < 76; i++){
     sum = linepoints[i][0]+linepoints[i][1]+linepoints[i][2]+linepoints[i][3];
    if(sum == 2){
        usefullines[i] = 1;
    }
}
int x, y,a, b, c;
int count = 0;
for(x = 0; x < 76; x++){
    if(usefullines[x] == 1){
        for(y = 0; y < 4; y++){
            if(linepoints[x][y] == 0){
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 1;
                for (b = 0;b < 76; b++){
        for(c = 0; c < 4; c++){
            int val =   temparray[lines[b][c][0]][lines[b][c][1]][lines[b][c][2]];
         linepointstest[b][c] = val;
        }
        
    }
                
                
                for(a = 0; a < 76; a++){
               sum = linepointstest[a][0]+linepointstest[a][1]+linepointstest[a][2]+linepointstest[a][3];
                   
               if (sum == 3){
                        count++;
                        
                    }
                    
                }
                if(count > 1){
                        forkdetected = true;
                        
                        blockfork[0] = lines[x][y][0];
                        blockfork[1] = lines[x][y][1];
                        blockfork[2] = lines[x][y][2];
                    }
                
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 0;
                count = 0;
            } 
            }
        }
    
}

System.out.println(count);
return forkdetected;
}
public static int[] playfork(int[][][] j){
    int[][][] temparray = j;
    int[] playfork = new int[3];
    boolean forkdetected = false;
    int[][] linepointstest = new int[76][4];
    int[][] linepoints = new int[76][4];
    int i,l,k;
    int[] usefullines = new int[76];
    for (i = 0;i < 76; i++){
        for(l = 0; l < 4; l++){
            int val =   temparray[lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         linepoints[i][l] = val;
        }
        
    }
    int sum;
for(i = 0; i < 76; i++){
     sum = linepoints[i][0]+linepoints[i][1]+linepoints[i][2]+linepoints[i][3];
    if(sum == 2){
        usefullines[i] = 1;
    }
}
int x, y,a, b, c;
int count = 0;
for(x = 0; x < 76; x++){
    if(usefullines[x] == 1){
        for(y = 0; y < 4; y++){
            if(linepoints[x][y] == 0){
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 1;
                for (b = 0;b < 76; b++){
        for(c = 0; c < 4; c++){
            int val =   temparray[lines[b][c][0]][lines[b][c][1]][lines[b][c][2]];
         linepointstest[b][c] = val;
        }
        
    }
                
                
                for(a = 0; a < 76; a++){
               sum = linepointstest[a][0]+linepointstest[a][1]+linepointstest[a][2]+linepointstest[a][3];
                   
               if (sum == 3){
                        count++;
                        
                    }
                    
                }
                if(count > 1){
                        forkdetected = true;
                        
                        playfork[0] = lines[x][y][0];
                        playfork[1] = lines[x][y][1];
                        playfork[2] = lines[x][y][2];
                    }
                
                temparray[lines[x][y][0]][lines[x][y][1]][lines[x][y][2]] = 0;
                count = 0;
            } 
            }
        }
    
}

System.out.println(count);
return playfork;
}
public static int[] playliveline(int[][][] j){
    int[][] linepoints = new int[76][4];
    int[] coordinates = new int[3];
    int i,l,k;
    for (i = 0;i < 76; i++){
        for(l = 0; l < 4; l++){
            int val =   j[lines[i][l][0]][lines[i][l][1]][lines[i][l][2]];
         linepoints[i][l] = val;
        }
        
    }
    
    for (int u = 0; u < 76; u++){
        
        int sum = 0;
        sum = linepoints[u][0]+linepoints[u][1]+linepoints[u][2]+linepoints[u][3];
        if(sum == 0 || sum == 1 || sum == 2){
            
            for(int t = 0; t < 4; t++){
                
                if (linepoints[u][t] == 0){
                    
                    coordinates[0] = lines[u][t][0];
                    coordinates[1] = lines[u][t][1];
                    coordinates[2] = lines[u][t][2];
                }
                
            }
        }
    }
    return coordinates;
}
}