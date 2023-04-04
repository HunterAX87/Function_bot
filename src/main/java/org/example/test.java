import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;




public class test extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();                  //We moved this line out of the register method, to access it later
        botsApi.registerBot(bot);
        // bot.sendText(672678141L, "Hello World!");  //The L just turns the Integer into a Long
    }


    @Override
    public String getBotUsername() {
        return "First_function_bot";
    }

    @Override
    public String getBotToken() {
        return "5834801321:AAGkcia6FEnWTckJnZVTPkLy6SfaNAmkFc0";
    }



    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();


        //sendText(id, msg.getText());

        if(msg!=null && msg.hasText()){
            switch(msg.getText()){
                case "/start":
                    sendText(id,"Добро пожаловать в наш First function bot \nВыберите свой вариант(1-7)");
                    int v = Integer.parseInt(update.getMessage().getText());
                    sendText(id,"Введите аргументы a,b,c,d,x,y,n через пробел.");

                    String message = update.getMessage().getText();
                    String[] messageArray = message.split(" ");
                    double a = Double.parseDouble(messageArray[0]);
                    double b = Double.parseDouble(messageArray[1]);
                    double c = Double.parseDouble(messageArray[2]);
                    double d = Double.parseDouble(messageArray[3]);
                    double x = Double.parseDouble(messageArray[4]);
                    double y = Double.parseDouble(messageArray[5]);
                    double n = Double.parseDouble(messageArray[6]);
                    double result = 0;

                    switch (v) {
                        case 0:
                            result = c+ b + (d * y);
                            break;
                        case 1:
                            result = a + b + (x * n);
                            break;
                        case 2:
                            result = x + y + (a * b);
                            break;
                        case 3:
                            result = x * x + (a + b - y);
                            break;
                        default:
                            sendText(update.getMessage().getChatId(), "Некорректный номер варианта.");
                            return;
                    }
                    String response = String.format("Номер варианта: "+v+"\nАргументы: a= "+a+", b= "+b+", c= "+c+", d= "+d+", x= "+x+", y= "+y+", n= "+n+".\nРезультат= "+result);
                    sendText(id, response);
                default:
                    sendText(update.getMessage().getChatId(), "Некорректный .");
                    return;
            }
        }
    }



    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

}




// Update(updateId=408977026, message=Message(messageId=12, from=User(id=672678141, firstName=Abdukhalikov♚X♚, isBot=false, lastName=null, userName=HunterAX87, languageCode=ru,
