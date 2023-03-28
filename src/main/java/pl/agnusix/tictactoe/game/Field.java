package pl.agnusix.tictactoe.game;

import java.util.Objects;
import java.util.Optional;

public class Field {

    private State  state;

    public Field(State state) {
        this.state = state;
    }

    public static Field emptyField() {
        return new Field(State.EMPTY);
    }
//tu piszemy xD

    public boolean isOccupied() {
        return !State.EMPTY.equals(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return state == field.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return "Field{" +
                "state=" + state +
                '}';
    }

    public void set(Sign sign) {
        state = Sign.X.equals(sign) ?  //ternary operator
                State.X :
                State.O;
//        if(sign.equals(Sign.X)) {
//            state = State.X;
//        } else{
//            state = State.O;
//        }
    }
    public Optional<Sign> occupiedBy(){
        if (State.EMPTY.equals(state)){
            return Optional.empty();
        }
        Sign sign = State.X.equals(state) ?
                Sign.X :
                Sign.O;
        return Optional.of(sign);
    }
    public String getState(){
        return state.name();
    }
    private enum State{
        X,
        O,
        EMPTY
    }
}
