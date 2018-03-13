package CackeProject.Entities;


public enum Unit {

    Kg {
        @Override
        public String toString() {
            return "Kg";
        }
    },
    Piece
            {
                @Override
                public String toString() {
                    return "Par pièce";
                }
            },
    Slice
            {
                @Override
                public String toString() {
                    return "Par morceau";
                }
            }
}
