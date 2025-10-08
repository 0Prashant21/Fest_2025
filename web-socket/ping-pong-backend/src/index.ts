import { WebSocketServer } from "ws";

const wss = new WebSocketServer({ port: 8080 });

wss.on("connection", function (socket) {
  // console.log("Connection established...");
  // socket.send("hey there!");
  // setInterval(() => {
  //   socket.send("Current price of Solana is " + Math.random());
  // }, 5000);

  socket.on("message", (e) => {
    if (e.toString() === "ping") {
      socket.send("pong");
    }
  });

  // socket.on("message", (e) => {
  //   console.log(e.toString());
  // });
});
