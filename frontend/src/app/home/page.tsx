'use client';

import Nav from '@/shared/ui/Nav';
import Card from './Card';

export default function Pages() {
  return (
    <div>
      <Nav />
     
      <ul className='pt-8 grid grid-cols-2 gap-8 place-items-center'>
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
      </ul>
    </div>
  );
}