export default function Card() {
  return (
    <li className='flex flex-col gap-4  w-[420px]'>
      <div className='flex flex-col justify-center items-center h-[420px] gap-4 border border-gray-50 border-solid rounded-lg bg-gray-50'>
        <div className='w-[300px] h-[300px] rounded-full bg-gray-200' />

        <p>안녕하세요, 어제보다 오늘 더 성장하는 개발자 김OO입니다.</p>
      </div>

      <div className='flex justify-between items-center'>
        <p className='font-semibold'>김OO</p>

        <p className='w-[60px] text-center p-1 border border-[#88cc50] rounded-3xl text-[#88cc50] '>
          개발자
        </p>
      </div>
    </li>
  );
}
