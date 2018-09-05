package ktm.com.threadtt;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //cria objeto handler que irá lidar com o processo de execução na thread
    private Handler handler = new Handler();

    //Utilizar o Runnable sempre que quiser rodar um processo em uma thread
    private Runnable runnableCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia a interface Runnable com o método run, que será executado pela thread
        runnableCodigo = new Runnable() {
            @Override
            public void run() {
                //aqui irá o bloco de códigos que fará a execução de tempos em tempos
                Toast.makeText(getApplicationContext(), "Executado o código", Toast.LENGTH_LONG).show();

                //executa o objeto runnableCodigo a cada 10 segundo, configure aqui o tempo
                handler.postDelayed(runnableCodigo, 10000);
            }
        };

        /*
            faz a primeira execução do código, uma vez iniciado, dentro do código será
            mantido sua execução, criando um processo pós execução.
        */
        handler.post(runnableCodigo);

    }

    @Override
    protected void onStop() {
        super.onStop();
        /*Remove a execução caso o usuário não esteja utilizando a aplicação
        é importante parar a execução caso não precise mais, para não deixar rodando
        quando desnecessário
        */
        handler.removeCallbacks(runnableCodigo);
    }

}