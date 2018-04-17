package net.unadeca.ana.myrest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.unadeca.ana.myrest.database.models.PedidosTable;
import net.unadeca.ana.myrest.util.TodoViewHolder;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

public class mostrarpedidos extends AppCompatActivity {
    private RecyclerView lista;
    private static Context QuickContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarpedidos);
        QuickContext = this;
        FlowManager.init(this);
        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));

        List<PedidosTable> info = SQLite.select().from(PedidosTable.class).queryList();
        lista.setAdapter(new ToDoAdapter(info));

    }

    public static class ToDoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
        private final List<PedidosTable> listPedidosTable;
        private final LayoutInflater inflater;

        public ToDoAdapter(List<PedidosTable> listPedidosTables) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listPedidosTable = listPedidosTables;
        }

        @Override
        public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.objeto, parent, false);
            return new TodoViewHolder(view);
        }

        public void animateTo(List<PedidosTable> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<PedidosTable> newModels) {
            for (int i = listPedidosTable.size() - 1; i >= 0; i--) {
                final PedidosTable model = listPedidosTable.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<PedidosTable> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final PedidosTable model = newModels.get(i);
                if (!listPedidosTable.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<PedidosTable> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final PedidosTable model = newModels.get(toPosition);
                final int fromPosition = listPedidosTable.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public PedidosTable removeItem(int position) {
            final PedidosTable model = listPedidosTable.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, PedidosTable model) {
            listPedidosTable.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final PedidosTable model = listPedidosTable.remove(fromPosition);
            listPedidosTable.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onBindViewHolder(final TodoViewHolder holder, final int position) {
            PedidosTable current = listPedidosTable.get(position);
            holder.html.setHtml(ActividadAString(current),
                    new HtmlResImageGetter(holder.html));


        }
        private String ActividadAString(PedidosTable todo){
            String html = "<a><b>" +"Nombre del comprador: " + todo.nombrecomprador+"<b>";
            html+= "<br>" +"Pedido Detallado: " +todo.pedidodetallado+"</a>";
            html+= "<br>" +"Dirección: " +todo.direccion+"</a>";
            html+= "<br>" +"Número de Teléfono: "+todo.numtel+"</a>";
            return html;
        }


        @Override
        public int getItemCount() {
            return listPedidosTable.size();
        }




    }
    
}
