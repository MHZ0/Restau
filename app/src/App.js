import Header from './components/Header';
import Rmenu from './components/Rmenu';


function App() {
  return (
    <div className="bg-cover bg-center bg-blend-difference" style={{backgroundImage:"url(/images/background.jpg)"}} >
      <header className="">
        <Header />
      </header>
     <Rmenu />
     <footer className='bg-yellow-700'>
        <div className='text-center text-white'>
          <p>© 2023 - 2024</p>
        </div>
     </footer>
    </div>
  );
}

export default App;
