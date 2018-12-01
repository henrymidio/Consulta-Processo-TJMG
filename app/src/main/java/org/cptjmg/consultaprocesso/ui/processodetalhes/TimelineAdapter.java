package org.cptjmg.consultaprocesso.ui.processodetalhes;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.model.Movimentacao;
import org.cptjmg.consultaprocesso.util.CommonUtils;

import java.util.List;
import java.util.Map;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> {

    private List<Movimentacao> movimentacaoProcesso;

    public TimelineAdapter(List<Movimentacao> movimentacaoProcesso) {
       this.movimentacaoProcesso = movimentacaoProcesso;
    }

    @Override
    public TimelineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false);
        MyViewHolder vh = new MyViewHolder(layoutView, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("data no view holder", movimentacaoProcesso.get(position).getData());

        Map<String, String> data = CommonUtils.getTimelineDate(movimentacaoProcesso.get(position).getData());
        holder.ano.setText(data.get("ano"));
        holder.data.setText(data.get("dia"));

        holder.descricao.setText(movimentacaoProcesso.get(position).getDescricao());
        if(!movimentacaoProcesso.get(position).getComplemento().isEmpty()) {
            holder.complemento.setText(movimentacaoProcesso.get(position).getComplemento());
        } else {
            holder.complemento.setVisibility(View.GONE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movimentacaoProcesso.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView data;
        private TextView ano;

        public TimelineView mTimelineView;

        private TextView descricao;
        private TextView complemento;

        public MyViewHolder(View v, int viewType) {
            super(v);
            data = v.findViewById(R.id.data);
            ano = v.findViewById(R.id.ano);

            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);

            descricao = v.findViewById(R.id.descricao);
            complemento = v.findViewById(R.id.complemento);
        }

    }
}
