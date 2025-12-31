import { ResumeList } from '@/features/home/ResumeList';

export default function Home() {
  return (
    <div>
      <ul className='pt-8 grid grid-cols-2 gap-8 place-items-center'>
        <ResumeList />
      </ul>
    </div>
  );
}
