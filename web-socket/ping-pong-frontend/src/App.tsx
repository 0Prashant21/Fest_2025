import { useEffect, useRef, useState } from 'react';
import './App.css';

function App() {
  const [socket, setSocket] = useState<WebSocket | null>(null);
  const inputRef = useRef<HTMLInputElement | null>(null);

  function sendMsg() {
    if (!socket) return;

    const msg = inputRef.current?.value;

    socket.send(msg || '');
  }

  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8080');
    setSocket(ws);

    ws.onmessage = (e) => {
      alert(e.data);
    };
  }, []);

  return (
    <>
      <input ref={inputRef} type='text' placeholder='Message' />
      <button onClick={sendMsg} style={{ marginLeft: '10px' }}>
        Send
      </button>
    </>
  );
}

export default App;
