package rainbowscaledriver;

import java.math.BigDecimal;

public class UnitInterval implements Comparable {
    private BigDecimal value;
    // true: apply BigDecimal.remainder(1) to input value to force it in bounds
    // false: throw exception if input value out of bounds
    private final static boolean WRAP_BY_DEFAULT = false;
    
    // The Primary Constructor
    // BigDecimal value, specified wrap
    public UnitInterval( BigDecimal value, boolean wrap ) 
            throws IllegalArgumentException {
        BigDecimal v;
        
        if( wrap ) 
            v = value.remainder( BigDecimal.ONE );
        else
            v = value;
        
        if( v.compareTo( BigDecimal.ZERO ) < 0 
          || v.compareTo( BigDecimal.ONE ) > 0 )
            throw new IllegalArgumentException( 
                "Argument out of bounds."
            );
        this.value = v;
    }
    // BigDecimal value, default wrapping
    public UnitInterval( BigDecimal value ) {
        this( value, WRAP_BY_DEFAULT );
    }
    // double value, specified wrap
    public UnitInterval( double value, boolean wrap ) {
        this( new BigDecimal( value), wrap );
    }
    // double value, default wrapping
    public UnitInterval( double value ) {
        this( new BigDecimal( value ) );
    }
    public BigDecimal getValue() {
        return this.value;
    }
    public double getDouble() {
        return this.value.doubleValue();
    }
    public BigDecimal multiply( BigDecimal factor ) {
        return factor.multiply( this.value );
    }
    public UnitInterval multiply( UnitInterval ui ) {
        return new UnitInterval( this.multiply( ui.getValue() ) );
    }
    @Override
    public String toString() {
        return this.getValue().toString();
    }
    // Comparable
    @Override
    public int compareTo( Object obj ) {
        BigDecimal objValue = ( (UnitInterval) obj ).getValue();
        return this.getValue().compareTo( objValue );
    }
}