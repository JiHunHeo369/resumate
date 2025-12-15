'use client';
import { redirect } from 'next/navigation';
import { useState } from 'react';
import Button from './Button';

export default function Nav() {
  const [activeMenu, setActiveMenu] = useState<number>(-1);

  const handleMenuClick = ({ url, idx }: { url: string; idx?: number }) => {
    setActiveMenu(idx!);

    redirect(`/${url}`);
  };

  return (
    <nav className='flex items-center justify-between px-28 py-3 h-16 border-b  border-slate-200 sticky top-0 bg-white'>
      <div className='flex items-center gap-8 '>
        <div className='flex text-lg font-bold'>
          <h2>
            <button
              className='cursor-pointer'
              onClick={() => handleMenuClick({ url: 'home', idx: -1 })}
            >
              🍀 RESUMATE
            </button>
          </h2>
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
              <button
                className='appearance-none bg-none border-0 p-0 m-0 focus:outline-none cursor-pointer hover:text-[#88cc50]'
                onClick={() => handleMenuClick({ url: menu.id, idx })}
              >
                {menu.name}
              </button>
            </li>
          ))}
        </ul>
      </div>

      <Button
        className='cursor-pointer hover:opacity-90'
        onClick={() => handleMenuClick({ url: 'login' })}
      >
        로그아웃
      </Button>
    </nav>
  );
}

const MENU_MAP = [
  { id: 'create', name: '이력서 등록' },
  // { id: 'my', name: 'MY' },
];
