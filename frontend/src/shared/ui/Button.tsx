type TProps = {
    children: React.ReactNode;
	mode?: "default" | 'outline';
	className?: string;
	onClick: () => void;
};

export default function Button({ children, mode = "default", className, onClick }: TProps) {
	const borderColor = mode === "outline" ? "border-gray-400" : "border-transparent";
	const txtColor = mode === "outline" ? "text-gray-400" : "text-white";
	const bgColor = mode === 'outline' ? 'bg-white' : 'bg-[#88cc50]';

	return (
		<button
			onClick={onClick}
			className={`w-22 h-10 whitespace-nowrap border border-solid px-4 leading-9 rounded-lg text-sm ${borderColor} ${txtColor} ${bgColor} ${className}`}
		>
			{children}
		</button>
	);
}