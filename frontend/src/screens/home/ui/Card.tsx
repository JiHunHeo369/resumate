import Image from 'next/image';
import { useRouter } from 'next/navigation';
import { CardProps } from '@/shared/types/resume';

export default function Card({ data }: CardProps) {
  const router = useRouter();

  const goToDetail = () => {
    router.push(`/detail/${data.resumeId}`);
  };

  return (
    <li
      className='flex flex-col gap-4 w-[420px] cursor-pointer'
      onClick={goToDetail}
    >
      <div className='flex flex-col justify-center items-center h-[420px] gap-4 border border-gray-50 border-solid rounded-lg bg-gray-50'>
        <Image
          src={data.image}
          alt={data.userName}
          width={300}
          height={300}
          className='rounded-full object-cover'
        />

        <p>{data.introduction}</p>
      </div>

      <div className='flex justify-between items-center'>
        <p className='font-semibold'>{data.userName}</p>

        <p className='w-[80px] text-center p-1 border border-[#88cc50] rounded-3xl text-[#88cc50] '>
          {data.jobName}
        </p>
      </div>
    </li>
  );
}
