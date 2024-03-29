import React, { useState, useEffect } from 'react';
import MyModal from './MyCardModal';

const Rmenu = () => {
  const tableNumber = 1;

  const [menu, setMenu] = useState([]);
  const [loading, setLoading] = useState(false);
  const [order, setOrder] = useState({ mealsAndDrinks: [] });
  const [selectedItems, setSelectedItems] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleAdd = (index) => {
    setMenu((prevMenu) => {
      const updatedMenu = [...prevMenu];
      updatedMenu[index].quantity = (updatedMenu[index].quantity || 0) + 1;
      return updatedMenu;
    });
  };

  const handleRemove = (index) => {
    setMenu((prevMenu) => {
      const updatedMenu = [...prevMenu];
      updatedMenu[index].quantity = Math.max((updatedMenu[index].quantity || 0) - 1, 0);
      return updatedMenu;
    });
  };

  const handleOrder = () => {
    const selectedItems = menu.filter((item) => item.quantity > 0);
    if (selectedItems.length > 0) {
      const mealsAndDrinksArray = [];

      selectedItems.forEach((item) => {
        for (let i = 0; i < item.quantity; i++) {
          mealsAndDrinksArray.push({ id: item.id });
        }
      });

      const newOrder = {
        tableNumber,
        mealsAndDrinks: mealsAndDrinksArray,
      };

      fetch('/api/addorder', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newOrder),
      })
        .then((response) => response.json())
        .then((data) => {
          setOrder(data);
          setIsModalOpen(true);
        })
        .catch((error) => console.error('Error creating order:', error));
    }
  };

  useEffect(() => {
    setLoading(true);

    fetch('/api/menu')
      .then((response) => response.json())
      .then((data) => {
        setMenu(data);
        setLoading(false);
      })
      .catch((error) => console.error('Error fetching menu:', error));
  }, []);

  if (loading) {
    return <p className="flex items-center justify-center fixed top-0 left-0 right-0 h-screen text-xl">Loading...</p>;
  }

  return (
    <div className="flex flex-col items-center m-10 shadow-lg py-6 rounded text-white subpixel-antialiased filter brightness-104">
      <table className="border border-2 border-slate-700 rounded border-separate border-spacing-1 shadow-lg">
        <thead>
          <tr>
            <th colSpan={6} className="shadow-md bg-yellow-500 text-xl font-bold rounded">
              Menu
            </th>
          </tr>
        </thead>
        <tbody>
          {menu.map((meal, index) => (
            <tr key={meal.id}>
              <td className="border border-2 border-slate-700 p-2 text-lg text-center font-semibold rounded-lg shadow-md">{meal.id}</td>
              <td className="border border-2 border-slate-700 p-2 rounded shadow-md">
                <img src={meal.imagePath} alt="image here" className="flex items-center justify-center h-20 w-20 rounded" />
              </td>
              <td className="border border-slate-700 p-2 font-semibold text-lg text-center rounded shadow-md">{meal.name}:</td>
              <td className="border border-slate-700 p-2 rounded shadow-md">{meal.description}</td>
              <td className="border border-slate-700 p-2 text-center rounded shadow-md ">{meal.price}$</td>
              <td className="border border-slate-700 shadow-lg rounded-md shadow-md">
                <div className="flex items-center justify-around w-80">
                  <div className="flex flex-col w-30">
                    <button
                      onClick={() => handleAdd(index)}
                      className="bg-green-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded shadow-md m-1"
                    >
                      Add
                    </button>
                    <button
                      onClick={() => handleRemove(index)}
                      className="bg-red-600 hover:bg-red-800 text-white font-bold py-2 px-4 rounded shadow-md m-1"
                    >
                      Remove
                    </button>
                  </div>
                  <div className="flex flex-col items-center justify-center mr-2 w-40 ">
                    <h1 className="">{meal.quantity || 0}X</h1>
                    <h1 className="">{meal.name}</h1>
                  </div>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button
        onClick={handleOrder}
        className="bg-yellow-500 hover:bg-yellow-600 text-lg text-white font-bold py-2 px-32 rounded shadow-lg m-10"
      >
        Order
      </button>
      {isModalOpen && (
        <MyModal
          onClose={() => setIsModalOpen(false)}
          items={selectedItems}
          order={order}
          tbn={tableNumber}
        />
        )}
        {order.mealsAndDrinks.length > 0 && (
              <div className="flex flex-col items-center text-white text-2xl font-semibold">
                <p className='m-2'>Sit tight! Your order is being prepared!</p>
                <p className='m-5'>Total Bill: {order.bill}$</p>
              </div>
            )}
      <h className="text-black text-xl font-semibold">Your Table Number: {tableNumber}</h>
    </div>
  );
};

export default Rmenu;
