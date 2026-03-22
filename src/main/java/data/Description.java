package data;

@SuppressWarnings("SpellCheckingInspection")
public class Description {

    private String pcDescription;

    public Description() {
        pcDescription = "This is a special desktop  configuration, made by the best student of Pragmatic - Momchil Slavov.\n" +
                "This custom-built desktop  is a \"sleeper\" powerhouse for the low price we are selling it for. By pairing the world-renowned 5800X3D with the next-gen RX 9060 XT, this machine is optimized for ultra-low latency gaming and heavy-duty 1440p/4K content creation.\n" +
                "\n" +
                "- CPU: AMD Ryzen 7 5800X3D (8 Cores, 16 Threads)\n" +
                "Equipped with 96MB of L3 3D V-Cache, this remains a gaming champion, delivering massive frame rate stability in CPU-bound titles like Warzone, Simulators, and MMOs.\n" +
                "\n" +
                " - GPU: Gigabyte AMD Radeon RX 9060 XT (16GB GDDR6)\n" +
                "Architecture: RDNA 4.\n" +
                "The 16GB VRAM buffer ensures you won't hit a \"memory wall\" in modern AAA titles at high resolutions.\n" +
                "\n" +
                " - Motherboard: Gigabyte B570 AORUS Elite\n" +
                "Power Delivery: 12+2 Phase Digital VRM for rock-solid stability under heavy CPU loads.\n" +
                "Connectivity: Features PCIe 4.0 support, 2.5GbE LAN, and Wi-Fi 6 integrated.\n" +
                "\n" +
                " - RAM: 32GB (2 x 16GB) DDR4-3600MHz CL16\n" +
                "Dual-channel high-speed memory tuned to the \"sweet spot\" for Ryzen 5000 series processors.\n" +
                "\n" +
                " - OS Drive: 512GB NVMe PCIe Gen4 SSD\n" +
                "Sequential read speeds up to 5,000 MB/s for near-instant boot times.\n" +
                "\n" +
                " - Massive Library: 4TB 7200RPM SATA III HDD\n" +
                "Enough space for 80+ AAA games, 4K video archives, or a massive creative portfolio.\n" +
                "\n" +
                " - Power Supply (PSU): Corsair RM750e (750W) 80+ Gold Fully Modular\n" +
                "Provides high-efficiency power with plenty of headroom for the RX 9060 XT. Being fully modular means only the necessary cables are used, maximizing airflow.";
    }

    public String getPcDescription() {
        return pcDescription;
    }
}
