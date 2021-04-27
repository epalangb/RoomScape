package roomscape.es.roomscape.Presentación.Controller;


import roomscape.es.roomscape.Presentación.Command.Command;
import roomscape.es.roomscape.Presentación.Command.CommandFactory;
import roomscape.es.roomscape.Presentación.Command.Dispatcher.Dispatcher;
import roomscape.es.roomscape.Presentación.Eventos;
import roomscape.es.roomscape.Presentación.FactoryView.AbstractFactoryView;

public class ControllerImp extends Controller {

    public ControllerImp() {
        AbstractFactoryView.getInstance().createMainView();
    }

    public void action(Context contexto) {
        Command comando= CommandFactory.getInstance().parse(contexto.getEvento());
        Context respuestaComando=null;

        if(comando!=null){
            try {
                respuestaComando=comando.execute(contexto.getDatos());
            }
            catch(Exception e) {
                Context errorComando = new Context(null, Eventos.CommandError);
                Dispatcher.getInstance().createView(errorComando);
            }
        }
        if(respuestaComando!=null) {
            Dispatcher.getInstance().createView(respuestaComando);
        }
        else {
            Dispatcher.getInstance().createView(contexto);
        }

    }
}