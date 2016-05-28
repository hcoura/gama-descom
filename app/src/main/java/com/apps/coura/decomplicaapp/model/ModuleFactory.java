package com.apps.coura.decomplicaapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class ModuleFactory {

    public static ArrayList<Module> getListOfModules() {

        // todo: clear ***

        ArrayList<Module> modules = new ArrayList<>();

        // Module 1
        Module module1 = new Module("Módulo 1");
        VideoPage videoPage1 = new VideoPage("B_2I6anzgKY");

        QuizPage quizPage1 = new QuizPage("O governo de Vargas, no período de 1937 a 1945, pode ser considerado como sendo:");

        List<Answer> answers = Arrays.asList(
                new Answer("Uma ditadura socialista.", false),
                new Answer("Um parlamentarismo democrático.", false),
                new Answer("Um presidencialismo oligárquico.", false),
                new Answer("Um presidencialismo autocrático.***", true),
                new Answer("Um parlamentarismo populista.", false)
        );
        quizPage1.setAnswers(answers);

        VideoPage videoPage2 = new VideoPage("qE65BWjM7c4");

        QuizPage quizPage2 = new QuizPage("Sobre Leonardo da Vinci, é verdadeira afirmar que:");

        List<Answer> answers2 = Arrays.asList(
                new Answer("Foi o mais importante escultor e poeta do Renascimento Italiano.", false),
                new Answer("Foi um importante pintor, escultor, cientista, engenheiro, escritor e físico do Renascimento.***", true),
                new Answer("Foi um importante governante italiano que patrocinou vários artistas e cientistas do período renascentista.", false),
                new Answer("Foi um importante escultor e pintor italiano do Renascimento, cuja principal obra é Pietá.", false),
                new Answer("Nenhuma das opções.", false)
        );

        quizPage2.setAnswers(answers2);

        VideoPage videoPage3 = new VideoPage("xYnE_pOKVH0");

        QuizPage quizPage3 = new QuizPage("“Uma casa dividida contra si mesma não subsistirá. Acredito que esse governo, meio escravista e meio livre, " +
                "não poderá durar para sempre. Não espero que a União se dissolva; não espero que a casa caia. Mas espero que deixe de ser dividida. " +
                "Ela se transformará só numa coisa ou só na outra.” Abraham Lincoln, em 1858. Esse texto expressa a:");

        List<Answer> answers3 = Arrays.asList(
                new Answer("Posição política autoritária do presidente Lincoln.", false),
                new Answer("Perspectiva dos representantes do sul dos EUA.", false),
                new Answer("Proposta de Lincoln para abolir a escravidão.", false),
                new Answer("Proposição nortista para impedir a expansão para o Oeste.", false),
                new Answer("Preocupação de Lincoln com uma possível guerra civil.***", true)
        );

        quizPage3.setAnswers(answers3);

        VideoPage videoPage4 = new VideoPage("Cs75Nl5MDVs");

        QuizPage quizPage4 = new QuizPage("Entre as mudanças ocorridas no Brasil Colônia durante a União Ibérica (1580 -1640), destacam-se:");

        List<Answer> answers4 = Arrays.asList(
                new Answer("A introdução do tráfico negreiro, a invasão dos holandeses no Nordeste e o início da produção de tabaco no recôncavo Baiano.", false),
                new Answer("A expansão da economia açucareira no Nordeste, o estreitamento das relações com a Inglaterra e a expulsão dos jesuítas.", false),
                new Answer("A incorporação do Extremo-Sul, o início da exploração do ouro em Minas Gerais e a reordenação administrativa do território.", false),
                new Answer("A incorporação do Extremo-Sul, o início da exploração do ouro em Minas Gerais e a reordenação administrativa do território.", false),
                new Answer("A expansão da ocupação interna pela pecuária, a expulsão dos franceses e o incremento do bandeirismo.***", true)
        );

        quizPage4.setAnswers(answers4);

        List<VideoPage> videoPages1 = Arrays.asList(videoPage1, videoPage2, videoPage3, videoPage4);
        module1.setVideoPages(videoPages1);

        List<QuizPage> quizPages1 = Arrays.asList(quizPage1, quizPage2, quizPage3, quizPage4);
        module1.setQuizPages(quizPages1);

        // Module 2
        Module module2 = new Module("Módulo 2");
        VideoPage videoPage5 = new VideoPage("sDC3OYLQCyo");
        VideoPage videoPage6 = new VideoPage("sDC3OYLQCyo");
        List<VideoPage> videoPages2 = Arrays.asList(videoPage5, videoPage6);
        module2.setVideoPages(videoPages2);
        QuizPage quizPage5 = new QuizPage("Quiz Page 3");
        quizPage5.setAnswers(answers);
        QuizPage quizPage6 = new QuizPage("Quiz Page 4");
        quizPage6.setAnswers(answers);
        List<QuizPage> quizPages2 = Arrays.asList(quizPage5, quizPage6);
        module2.setQuizPages(quizPages2);

        modules.add(module1);
        modules.add(module2);

        return modules;

    }

}
