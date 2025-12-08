'use client';

export default function Pages() {
  return (
    <div className='flex flex-col gap-8'>
      <div className='flex justify-around items-center w-full'>
        <div className='w-[300px] h-[300px] rounded-full bg-gray-100' />

        <div className='flex flex-col items-end gap-3 ml-8'>
          <div className='text-3xl font-bold text-[#3f5e25]'>김위존</div>
          <div>안녕하세요, 어제보다 오늘 더 성장하는 김위존입니다.</div>
        </div>
      </div>

      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>희망직무</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <select className='w-full border border-gray-300 rounded h-10'>
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
      </div>

      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>스킬</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <select className='w-full border border-gray-300 rounded h-10'>
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
      </div>

      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>학력</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <div className='flex gap-4'>
          <input
            type='date'
            className='border border-gray-300 px-3 py-2 rounded w-full'
          />

          <div className='relative w-full'>
            <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
              학교
            </span>

            <input
              type='text'
              className='border border-gray-300 px-3 py-2 rounded '
            />
          </div>

          <div className='relative w-full'>
            <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
              학과
            </span>

            <input
              type='text'
              className='border border-gray-300 px-3 py-2 rounded'
            />
          </div>
        </div>
      </div>
    </div>
  );
}
