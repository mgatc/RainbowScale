package rainbowscaledriver;

import java.math.BigDecimal;

public class SingleColorDistribution {
    protected BigDecimal shift;
    // Current implementation uses the base5 scale, which results in the 
    // RainbowScale being discontinuous (purple doesn't "wrap" back to red).
    // If wrapping behavior is desired, use base6 in 
    protected Scale base6 = new Scale( SingleColorDistribution.SIX );
    protected Scale base5 = new Scale( SingleColorDistribution.FIVE );
    protected Scale base = base5;
    
    // Commonly used BigDecimal values
    protected final static BigDecimal TWO = new BigDecimal(2);
    protected final static BigDecimal FOUR = new BigDecimal(4);
    protected final static BigDecimal FIVE = new BigDecimal(5);
    protected final static BigDecimal SIX = new BigDecimal(6);
    
    // Used as parameters for the constructor
    // e.g. new SingleColorDistribution( SingleColorDistribution.RED )
    public final static BigDecimal RED = FOUR;
    public final static BigDecimal GREEN = BigDecimal.ZERO;
    public final static BigDecimal BLUE = TWO;
    
    // Use class static properties RED, GREEN, AND BLUE as parameters
    public SingleColorDistribution( BigDecimal shift ) {
        this.shift = shift;
    }
    public UnitInterval getValue( UnitInterval index ) {
        /*  Adjusting the index for the proper color
            1. Convert the unit interval to base-5 scale
            2. Subtract shift to slide the domain of scale values
               to proper domain of color
            3. Ensure the domain is positive by
               a. adding 6
               b. then taking the remainder of division by 6
        */
        BigDecimal i = this.base.getValue( index ); // 1
                   i = i.subtract( this.shift );    // 2
                   i = i.add( SIX );                // 3a
                   i = i.remainder( SIX );          // 3b
        
        /*  Now that the index is shifted, apply this function to create the 
            color distribution
                  y = max( min( -1 * abs( i - 2 ) + 2, 1 ), 0 )
         */
        BigDecimal  y = new BigDecimal( i.toString() );
                    y = y.subtract( TWO );
                    y = y.abs();
                    y = y.multiply( new BigDecimal(-1) );
                    y = y.add( TWO );
                    y = y.min( BigDecimal.ONE );
                    y = y.max( BigDecimal.ZERO );
                    
        return new UnitInterval(y);
    }
}