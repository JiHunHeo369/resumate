import Card from './Card';

export default function Home() {
  return (
    <div>
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
