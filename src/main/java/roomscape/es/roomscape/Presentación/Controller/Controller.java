package roomscape.es.roomscape.Presentación.Controller;


public abstract class Controller {

    private static Controller controlador = null;

    public abstract void action(Context contexto);

    public static Controller getInstance() {
        // begin-user-code
        // TODO Auto-generated method stub
        if (controlador == null) {
            controlador = new ControllerImp();
        }
        return controlador;
        // end-user-code
    }
}