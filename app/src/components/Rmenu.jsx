import { useState, useEffect } from 'react';

const Rmenu = () => {

    const [menu, setMenu] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch('/api/menu') // Same as : fetch('http://localhost:5000/api/menu')
            .then(response => response.json())
            .then(data => 
                {
                    setMenu(data);
                    setLoading(false);
                })
                
            }, []);
          
            if (loading) {
                return <p className='flex items-center justify-center fixed top-0 left-0 right-0 h-screen text-xl'>Loading...</p>;
        }

    return (
        <div className="flex flex-col items-center m-10 shadow-lg py-6 rounded ">
            <table className="border-collapse border border-slate-600">
                <thead className="bg-yellow-500 text-xl font-bold ">   
                    <tr>
                        <th colSpan={5}>
                            Menu
                        </th>     
                    </tr> 
                </thead>
                <tbody>
                    {menu.map((meal) => (
                        <tr key={meal.id}>
                            <td className="border border-slate-600 p-2 text-lg font-semibold">{meal.id}</td>
                            <td className="border border-slate-600 p-2 ">
                                <img src={meal.imagePath} alt="image here" className='flex items-center justify-center h-20 w-20 rounded'/>
                            </td>
                            <td className="border border-slate-600 p-2 font-semibold text-lg text-center">{meal.name}:</td>
                            <td className="border border-slate-600 p-2">{meal.description}</td>
                            <td className="border border-slate-600 p-2 text-center ">{meal.price}$</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            
        </div>
    )


}

export default Rmenu;
        