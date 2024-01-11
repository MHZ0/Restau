import { Dialog, Transition } from '@headlessui/react';
import { Fragment, useState } from 'react';

const MyModal = ({ order, onClose ,tbn}) => {
  const [isOpen, setIsOpen] = useState(true);

  const getUniqueItemsWithQuantity = (items) => {
    const uniqueItems = {};
    items.forEach((item) => {
      if (!uniqueItems[item.id]) {
        uniqueItems[item.id] = { ...item, quantity: 1 };
      } else {
        uniqueItems[item.id].quantity += 1;
      }
    });
    return Object.values(uniqueItems);
  };

  const uniqueItemsWithQuantity = getUniqueItemsWithQuantity(order.mealsAndDrinks);

  return (
    <>
      <Transition appear show={isOpen} as={Fragment}>
        <Dialog
          as="div"
          className="fixed inset-0 z-50 overflow-y-auto"
          onClose={() => setIsOpen(false)}
        >
          <div className="min-h-screen flex items-center justify-center shadow-lg">
            <Transition.Child
              as={Fragment}
              enter="ease-out duration-500"  // Adjust the duration to make it slower
              enterFrom="opacity-0"
              enterTo="opacity-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100"
              leaveTo="opacity-0"
            >
              <Dialog.Overlay className="fixed inset-0 bg-black opacity-50" />
            </Transition.Child>

            <Transition.Child
              as={Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 scale-95"
              enterTo="opacity-100 scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 scale-100"
              leaveTo="opacity-0 scale-95"
            >
              <div className="relative z-10 bg-white p-6 max-w-md mx-auto rounded-md">
                <Dialog.Title className=" font-semibold mb-4 flex justify-between">
                  <p className='text-lg'>Your Order</p>
                  <p className='text-md mt-1'> Table N°: {tbn} </p>
                </Dialog.Title>
                <ul className="divide-y divide-gray-300">
                  {uniqueItemsWithQuantity.map((item, index) => (
                    <li key={item.id} className="py-2">
                      <p>
                        {index + 1}. {item.name} - ${item.price} - Quantity: {item.quantity}
                      </p>
                    </li>
                  ))}
                </ul>

                <p className="mt-4 text-lg font-semibold border-t-2 border-gray-300">
                   Total Bill: {order.bill}$ 
                </p>

                <div className="mt-6 flex justify-end">
                  <button
                    onClick={() => setIsOpen(false)}
                    className="px-4 py-2 bg-gray-300 text-gray-800 shadow-lg rounded-md hover:bg-gray-400"
                  >
                    Close
                  </button>
                </div>
              </div>
            </Transition.Child>
          </div>
        </Dialog>
      </Transition>
    </>
  );
};

export default MyModal;