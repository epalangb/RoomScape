package roomscape.es.roomscape.presentacion.controller;


import roomscape.es.roomscape.presentacion.command.Command;
import roomscape.es.roomscape.presentacion.command.CommandFactory;
import roomscape.es.roomscape.presentacion.command.dispatcher.Dispatcher;
import roomscape.es.roomscape.presentacion.Eventos;
import roomscape.es.roomscape.presentacion.factory_view.AbstractFactoryView;

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