package rainbowscaledriver;

import java.math.BigDecimal;

public class RainbowScaleDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int j=0;
        RainbowScale s = new RainbowScale();
        BigDecimal factor = new BigDecimal( 100 );
        
        // BigDecimal FOR loop
        for( BigDecimal i=new BigDecimal(0); 
                    i.compareTo( BigDecimal.ONE )<0; 
                            i=i.add(new BigDecimal("0.01")) ) {
            // loop logic
            System.out.println( i + " " +s.hexColor(new UnitInterval(i)));
        }  
    }
}
