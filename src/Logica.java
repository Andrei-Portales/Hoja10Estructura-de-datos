import java.util.ArrayList;

import javax.swing.JOptionPane;
  public class Logica
 {
    
	  
 public static String floyd(long[][] adyacencia,int pos1,int pos2)
 {
                int n=adyacencia.length;
                long D[][]=adyacencia.clone();
 
                String enlaces[][]=new String [n][n];
                String[][] aux_enlaces=new String[adyacencia.length][adyacencia.length];
 
                     for (int i = 0; i < n; i++) {
                         for (int j = 0; j < n; j++) {
                              enlaces[i][j]="";
                              aux_enlaces[i][j]="";
                        }
                     }
                String enl_rec="";
                   for (int k = 0; k < n; k++) {
                      for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                                       float aux=D[i][j];
                                       float aux2=D[i][k];
                                       float aux3=D[k][j];
                                       float aux4=aux2+aux3;
                                       float res=Math.min(aux,aux4);
                                       if(aux!=aux4)
                                        {
                                          if(res==aux4)
                                             {
                                                      enl_rec="";
                                                      aux_enlaces[i][j]=k+"";
                                                      enlaces[i][j]=enlaces(i,k,aux_enlaces,enl_rec)+(k+1);
                                             }
                                        }
                                     D[i][j]=(long) res;
                                }
                         }
                }
 
               
                String enlacesres="";
                   for (int i = 0; i <n; i++) {
                          for (int j = 0; j < n; j++) {
                                 if(i!=j)
                                     {
                                	 if ((i == pos1 && j == pos2)) {
                                		 if(enlaces[i][j].equals("")==true)
                                         {
                                           enlacesres+=(i+1)+", "+(j+1);
                                         }
                                      else
                                      enlacesres+=(i+1)+", "+enlaces[i][j]+", "+(j+1);
                                	 }
                                }
                          }
                   }
 
                    return enlacesres;
              }
 
        private static String enlaces(int i,int k,String[][] aux_enlaces,String enl_rec)
          {
            if(aux_enlaces[i][k].equals("")==true)
              {
                return "";
              }
               else
               {
            	   	enl_rec += enlaces(i,Integer.parseInt(aux_enlaces[i][k].toString()),aux_enlaces,enl_rec)+(Integer.parseInt(aux_enlaces[i][k].toString())+1)+", ";
                return enl_rec;
               }
          }
        
        public static long[][] floyd(long[][] adyacencia)
        {
                       int n=adyacencia.length;
                       long D[][]=adyacencia.clone();
        
                       String enlaces[][]=new String [n][n];
                       String[][] aux_enlaces=new String[adyacencia.length][adyacencia.length];
        
                            for (int i = 0; i < n; i++) {
                                for (int j = 0; j < n; j++) {
                                     enlaces[i][j]="";
                                     aux_enlaces[i][j]="";
                               }
                            }
                       String enl_rec="";
                          for (int k = 0; k < n; k++) {
                             for (int i = 0; i < n; i++) {
                               for (int j = 0; j < n; j++) {
                                              float aux=D[i][j];
                                              float aux2=D[i][k];
                                              float aux3=D[k][j];
                                              float aux4=aux2+aux3;
                                              float res=Math.min(aux,aux4);
                                              if(aux!=aux4)
                                               {
                                                 if(res==aux4)
                                                    {
                                                         enl_rec="";
                                                         aux_enlaces[i][j]=k+"";
                                                         enlaces[i][j]=enlaces(i,k,aux_enlaces,enl_rec)+(k+1);
                                                    }
                                               }
                                            D[i][j]=(long) res;
                                       }
                                }
                       }
                           return D;
                     }
        
        
        
        public static int graphCenter(long[][] matriz){
            ArrayList<Long> maximum = new ArrayList<>();
            ArrayList<Long> summation = new ArrayList<>();
            long max;
            int iterationCounter = 0;
            
            while(iterationCounter != matriz.length){
                max = 0;
                for (int i = 0; i < matriz.length; i++) {
                    summation.add(matriz[i][iterationCounter]);
                }
                for(Long l: summation){
                    if((l <= 10000000) && (l != 0)){
                        if(l > max){
                            max = l;
                        }
                    }
                }
                maximum.add(max);
                summation.clear();
                iterationCounter++;
            }
            
            max = 0;
            iterationCounter = 0;
            for(Long l: maximum){
                if(l > max){
                    max = l;
                }
            }

            int pos = maximum.indexOf(max);
            long min = max;       
            for (int i = 0; i < matriz.length; i++) {
                if((matriz[i][pos]<=10000000) && (matriz[i][pos]!=0)){
                    if(matriz[i][pos] < min){
                        min = matriz[i][pos];
                    }
                }
            }
            
            int center = 0;
            for (int i = 0; i < matriz.length; i++) {
                if(matriz[i][pos] == min){
                    center = i;
                    break;
                }
            }
            
            return center+1;
        }
        
        
        
 }