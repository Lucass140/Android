package com.example.multimediaapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//Importo librerias del video web
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configuracion del VideoView para reproducir un video local
        VideoView videoView = findViewById(R.id.videoView);
        //Construye la URI del video en la carpeta raw
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.redbull;
        //Convierte la cadena en un URI valido
        Uri uri = Uri.parse(videoPath);
        //Establece el video en el VideoView usando la URI
        videoView.setVideoURI(uri);

        //Agregar controles de reproduccion al VideoView
        MediaController mediaController = new MediaController(this);
        //Crea un Controlador de medios
        videoView.setMediaController(mediaController);
        //Asigna el Controlador de medios al VideoView
        mediaController.setAnchorView(videoView);
        //Inicia la reproduccion del video
        videoView.start();

        //Configuracion del WebView para cargar un video de Youtube
        WebView webView = findViewById(R.id.webView);
        //Obtiene la configuracion del WebView
        WebSettings webSettings = webView.getSettings();
        //Habilita JavaScript para poder cargar contenido dinamico
        webSettings.setJavaScriptEnabled(true);
        //URL del video de Youtube en formato embed
        String videoUrl = "https://www.youtube.com/embed/AC5m5vfFooI";
        //Asegura que el contenido se cargue en el WebView en lugar de abrir un navegador externo
        webView.setWebViewClient(new WebViewClient());
        //Carga la URL del video en el WebView
        webView.loadUrl(videoUrl);

        //Configura el boton para reproducir un sonido cuando se presiona
        findViewById(R.id.reproducir).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.auto);
                mediaPlayer.start();

                //Listener para liberar los recursos del MediaPlayer cuando el sonido termina
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
        });

    }
}