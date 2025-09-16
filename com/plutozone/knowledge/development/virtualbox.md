# com.plutozone.knowledge.development.VirtualBox


- command
```bash
$ VBoxManage list vms								# VM 목록
$ VBoxManage list runningvms						# 실행중인 VM 목록
$ VBoxManage startvm vm-001 --type headless			# vm-001 실행: gui(기본값: Window + Menu 모드), sdl(Window 모드), headless(NON 모드)
$ VBoxManage showvminfo avahi_test01 | grep MAC		# 실행중인 VM 정보 등
NIC 1:           MAC: 080027C96441, Attachment: Host-only...

$ arp -a | grep 08:00:27:c9:64:41
? (192.168.56.51) at 08:00:27:c9:64:41 [ether] on vboxnet0
```