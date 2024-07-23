package fun.teamti.goofyenchants.lib.base.tuple;

public record IntPair(int x, int y) {
    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj){
        if ( obj == this ) return true;
        if ( obj instanceof IntPair other ){
            return (x == other.x()) && (y == other.y());
        }
        return false;
    }

    @Override
    public int hashCode() { return (x * 17) + y; }
}
