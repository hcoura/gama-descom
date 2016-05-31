package com.apps.coura.decomplicaapp.model;

import com.apps.coura.decomplicaapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class ModuleFactory {

    public static ArrayList<Module> getListOfModules() {

        ArrayList<Module> modules = new ArrayList<>();

        // Module 1
        Module module1 = new Module("História virou música! 1");
        module1.setIcon(R.drawable.mod1);

        VideoPage videoPage1 = new VideoPage("-DFgaH-X4PA");
        videoPage1.setVideoTitle("Rap do Nazismo - História virou música!");

        QuizPage quizPage1 = new QuizPage("O nazismo é uma ideologia anticomunista que além de odiarem os negros e os judeus, também odiava:");
        quizPage1.setMaxPoints(50);
        quizPage1.setGoldCoins(2);

        List<Answer> answers = Arrays.asList(
                new Answer("As mulheres e os trabalhadores", false),
                new Answer("Os fascistas e os estrangeiros", false),
                new Answer("Os homossexuais e os soviéticos", true),
                new Answer("A raça ariana e as mulheres", false),
                new Answer("Os homossexuais e os estrangeiros", false)
        );

        quizPage1.setAnswers(answers);

        VideoPage videoPage2 = new VideoPage("qE65BWjM7c4");
        videoPage2.setVideoTitle("Renascimento Artístico - História virou música!");

        QuizPage quizPage2 = new QuizPage("Sobre Leonardo da Vinci, é verdadeira afirmar que:");
        quizPage2.setMaxPoints(50);
        quizPage2.setGoldCoins(2);

        List<Answer> answers2 = Arrays.asList(
                new Answer("Foi o mais importante escultor e poeta do Renascimento Italiano", false),
                new Answer("Foi um importante pintor, escultor, cientista, engenheiro, escritor e físico do Renascimento", true),
                new Answer("Foi um importante governante italiano que patrocinou vários artistas e cientistas do período renascentista", false),
                new Answer("Foi um importante escultor e pintor italiano do Renascimento, cuja principal obra é Pietá", false),
                new Answer("Nenhuma das opções", false)
        );

        quizPage2.setAnswers(answers2);

        VideoPage videoPage3 = new VideoPage("xYnE_pOKVH0");
        videoPage3.setVideoTitle("EUA no Século XIX - História virou música!");

        QuizPage quizPage3 = new QuizPage("“Uma casa dividida contra si mesma não subsistirá. Acredito que esse governo, meio escravista e meio livre, " +
                "não poderá durar para sempre. Não espero que a União se dissolva; não espero que a casa caia. Mas espero que deixe de ser dividida. " +
                "Ela se transformará só numa coisa ou só na outra.” Abraham Lincoln, em 1858. Esse texto expressa a:");
        quizPage3.setMaxPoints(50);
        quizPage3.setGoldCoins(2);

        List<Answer> answers3 = Arrays.asList(
                new Answer("Posição política autoritária do presidente Lincoln", false),
                new Answer("Perspectiva dos representantes do sul dos EUA", false),
                new Answer("Proposta de Lincoln para abolir a escravidão", false),
                new Answer("Proposição nortista para impedir a expansão para o Oeste", false),
                new Answer("Preocupação de Lincoln com uma possível guerra civil", true)
        );

        quizPage3.setAnswers(answers3);

        VideoPage videoPage4 = new VideoPage("Cs75Nl5MDVs");
        videoPage4.setVideoTitle("União Ibérica - História virou música!");

        QuizPage quizPage4 = new QuizPage("Entre as mudanças ocorridas no Brasil Colônia durante a União Ibérica (1580 -1640), destacam-se:");
        quizPage4.setMaxPoints(50);
        quizPage4.setGoldCoins(2);

        List<Answer> answers4 = Arrays.asList(
                new Answer("A introdução do tráfico negreiro, a invasão dos holandeses no Nordeste e o início da produção de tabaco no recôncavo Baiano", false),
                new Answer("A expansão da economia açucareira no Nordeste, o estreitamento das relações com a Inglaterra e a expulsão dos jesuítas", false),
                new Answer("A incorporação do Extremo-Sul, o início da exploração do ouro em Minas Gerais e a reordenação administrativa do território", false),
                new Answer("A incorporação do Extremo-Sul, o início da exploração do ouro em Minas Gerais e a reordenação administrativa do território", false),
                new Answer("A expansão da ocupação interna pela pecuária, a expulsão dos franceses e o incremento do bandeirismo", true)
        );

        quizPage4.setAnswers(answers4);

        List<VideoPage> videoPages1 = Arrays.asList(videoPage1, videoPage2, videoPage3, videoPage4);
        module1.setVideoPages(videoPages1);

        List<QuizPage> quizPages1 = Arrays.asList(quizPage1, quizPage2, quizPage3, quizPage4);
        module1.setQuizPages(quizPages1);

        // Module 2
        Module module2 = new Module("História virou música! 2");
        module2.setIcon(R.drawable.mod2);
        module2.setLockedIcon(R.drawable.mod2_locked);

        VideoPage videoPage5 = new VideoPage("Mw0Vjbz1Oo4");
        videoPage5.setVideoTitle("Tempos Modernos - História virou música!");

        QuizPage quizPage5 = new QuizPage("Nicolau Maquiavel (1469-1567), pensador florentino, afirmava que a obrigação suprema do " +
                "governante é manter o poder e a segurança do país que governa. E, para atingir seus objetivos, o governante deve usar " +
                "de todos os meios disponíveis, pois “os fins justificam os meios”. Suas idéias ficaram imortalizadas na obra:");
        quizPage5.setMaxPoints(50);
        quizPage5.setGoldCoins(2);

        List<Answer> answers5 = Arrays.asList(
                new Answer("Leviatã", false),
                new Answer("Política Segundo as Sagradas Escrituras", false),
                new Answer("A Arte da Guerra", false),
                new Answer("A Divina Comédia", false),
                new Answer("O Príncipe", true)
        );
        quizPage5.setAnswers(answers5);

        VideoPage videoPage6 = new VideoPage("bUXlennUp80");
        videoPage6.setVideoTitle("A Revolução Francesa - História virou música!");

        QuizPage quizPage6 = new QuizPage("Durante o período Napoleônico (1799 - 1815), entre as medidas adotadas por Bonaparte, assinale " +
                "aquela que teve repercussões importantes nas relações comerciais do Brasil com a Inglaterra:");
        quizPage6.setMaxPoints(50);
        quizPage6.setGoldCoins(2);

        List<Answer> answers6 = Arrays.asList(
                new Answer("Restauração financeira, com a consequente fundação do Bando da França, em 1800", false),
                new Answer("Decretação do Bloqueio Continental, em 1806, com o qual Napoleão visava arruinar a indústria e o comércio ingleses", true),
                new Answer("Promulgação, em 1804, do Código Civil, que incorporou definitivamente à legislação francesa os princípios liberais burgueses", false),
                new Answer("Expansão territorial da França, graças à incorporação de várias regiões da Europa, formando o chamado \"Império Napoleônico\"", false),
                new Answer("Criação do franco como novo padrão monetário", false)
        );

        quizPage6.setAnswers(answers6);

        VideoPage videoPage7 = new VideoPage("cAQDD-5M1r4");
        videoPage7.setVideoTitle("República Velha - História virou música!");

        QuizPage quizPage7 = new QuizPage("O que era a \"Política do café com leite\" na República Velha?");
        quizPage7.setMaxPoints(50);
        quizPage7.setGoldCoins(2);

        List<Answer> answers7 = Arrays.asList(
                new Answer("Foi o período em que o Brasil exportava em grande quantidade café e leite", false),
                new Answer("Foi uma política de revezamento do poder nacional pelos estados de São Paulo e Minas Gerais", true),
                new Answer("Foi o período em que o estado de São Paulo era o maior produtor de café e leite", false),
                new Answer("Foi o momento em que o poder político e econômico era concentrado nas mãos dos coronéis, que tomavam bastante café com leite", false),
                new Answer("Nenhuma das opções", false)
        );

        quizPage7.setAnswers(answers7);

        VideoPage videoPage8 = new VideoPage("B_2I6anzgKY");
        videoPage8.setVideoTitle("Erva Vargas - História virou música!");

        QuizPage quizPage8 = new QuizPage("O governo de Vargas, no período de 1937 a 1945, pode ser considerado como sendo:");
        quizPage8.setMaxPoints(50);
        quizPage8.setGoldCoins(2);

        List<Answer> answers8 = Arrays.asList(
                new Answer("Uma ditadura socialista", false),
                new Answer("Um parlamentarismo democrático", false),
                new Answer("Um presidencialismo oligárquico", false),
                new Answer("Um presidencialismo autocrático", true),
                new Answer("Um parlamentarismo populista", false)
        );

        quizPage8.setAnswers(answers8);

        List<VideoPage> videoPages2 = Arrays.asList(videoPage5, videoPage6, videoPage7, videoPage8);
        module2.setVideoPages(videoPages2);
        List<QuizPage> quizPages2 = Arrays.asList(quizPage5, quizPage6, quizPage7, quizPage8);
        module2.setQuizPages(quizPages2);

        // Module 3
        Module module3 = new Module("Módulo 3");
        module3.setLockedIcon(R.drawable.mod3_locked);
        module3.setIcon(R.drawable.mod3_locked);

        modules.add(module1);
        modules.add(module2);
        modules.add(module3);

        return modules;

    }

}
