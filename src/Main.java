
//{ "vertical" : "UP", "horizontal" : "LEFT" }

// Entry
public class Main {
    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ?
                Integer.parseInt(args[0]) : 42431;
        new Server(port).run();
    }
}
