// Fig. 27.14: Rotator.Java
// Um JavaBean que faz rota��o de an�ncios.
package UFJF;

public class Rotator 
{
   private String images[] = { "imagens/facebook.png",
      "imagens/google.jpg", "imagens/instagram.jpg" };
      
   private String links[] = {
      "https://www.facebook.com/",
      "https://plus.google.com/",
      "https://www.instagram.com/"};
         
   private int selectedIndex = 0;

   // retorna o nome do arquivo de imagem ao an�ncio atual
   public String getImage()
   {
      return images[ selectedIndex ];
   } // fim do m�todo getImage 

   // retorna o URL ao site Web correspondente ao an�ncio
   public String getLink()
   {
      return links[ selectedIndex ];
   } // fim do m�todo getLink 

   // atualiza selectedIndex assim as pr�ximas chamadas para getImage e
   // getLink retornam um an�ncio diferente
   public void nextAd()
   {
      selectedIndex = ( selectedIndex + 1 ) % images.length;
   } // fim do m�todo nextAd 
} // fim da classe Rotator 
