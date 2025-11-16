import { useState } from 'react';
import Button from './Button';

export default function Nav() {
  const [activeMenu, setActiveMenu] = useState(0);

  return (
    <nav className='flex items-center justify-between px-28 py-3 h-16 border-b  border-slate-200 sticky top-0 bg-white'>
      <div className='flex items-center gap-8 '>
        <div className='flex text-lg font-bold'>
          <h2>RESUMATE</h2>
        </div>

        <ul className='flex items-center text-base font-semibold text-black gap-1 list-none p-0 m-0'>
          {MENU_MAP.map((menu, idx) => (
            <li
              key={idx}
              className={
                idx === activeMenu
                  ? 'px-2 py-5 text-[#88cc50] font-bold text-none'
                  : 'px-2 py-5'
              }
            >
              <button className='appearance-none bg-none border-0 p-0 m-0 focus:outline-none'>
                {menu}
              </button>
            </li>
          ))}
        </ul>
      </div>

      <Button onClick={() => console.log('로그인 클릭')}>로그인</Button>
    </nav>
  );
}

const MENU_MAP = ['HOME', '이력서 등록', 'MY'];
