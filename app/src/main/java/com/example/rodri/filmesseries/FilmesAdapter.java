package com.example.rodri.filmesseries;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import java.util.List;



public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder> {

    private List<Filmes> filmes;
    private OnFilmesClickListener listener;

    public interface OnFilmesClickListener {
        void onFilmesClick(View view, int position);
    }

    public void setOnFilmesClickListener(OnFilmesClickListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaNoticia = inflater.inflate(R.layout.list_filme, parent, false);
        ViewHolder viewHolder = new ViewHolder(linhaNoticia);

        return viewHolder;
    }


    public FilmesAdapter(List<Filmes> Filmes) {
        this.filmes = Filmes;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ViewHolder imageViewHolder = (ViewHolder) holder;
        holder.txtTitulo.setText(filmes.get(position).getTitulo());
        holder.txtPopularidade.setText(filmes.get(position).getPopularidade());
        holder.txtDate.setText(filmes.get(position).getDate());

        final Context glideContext = holder.imgVImagem.getContext();
        Glide.with(glideContext).asBitmap().load(filmes.get(position).getImagem()).into(new BitmapImageViewTarget(imageViewHolder.imgVImagem) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(glideContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageViewHolder.imgVImagem.setImageDrawable(circularBitmapDrawable);
            }
        });






    }

    @Override
    public int getItemCount() {
        if(filmes == null)
            return 0;
        return filmes.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitulo;
        public ImageView imgVImagem;
        public TextView txtPopularidade;
        public TextView txtDate;


        public ViewHolder(View itemView) {
            super(itemView);
            imgVImagem = itemView.findViewById(R.id.filme_imagem);
            txtTitulo = itemView.findViewById(R.id.filme_titulo);
            txtPopularidade = itemView.findViewById(R.id.filme_pop);
            txtDate = itemView.findViewById(R.id.filme_data);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onFilmesClick(v, position);
                        }
                    }
                }
            });

        }

    }



}