package pl.agnusix.tictactoe.game;

import java.util.Objects;

public class Field {

    private State  state;

    public Field(State state) {
        this.state = state;
    }
//tu piszemy xD

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


    private enum State{
        X,
        O,
        EMPTY
    }

}
