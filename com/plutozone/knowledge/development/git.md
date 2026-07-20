# com.plutozone.knowledge.development.Git


## Temp
- reset(현재 버전을 삭제하고 이전 버전으로) vs. revert(현재 버전을 남기면서 신규 버전 생성)
- restore
- git flow is process or tools
- merge vs. rebase 그러나 결과는 같다.


## Overview
- `Working Tree` vs. `Staging Area` vs. `Repository(local vs. Remote)`
- `Untracked files` vs. `Changes to be committed`


## Version & Branch
> Git의 기능은 버전(변경) 관리다.
```bash
$ git init .				# initialization(git init . vs. git init [FOLDER])
$ git status				# show working tree status
$ git add [FILE]			# add [FILE] to staging area
$ git add .					# add all to staging area
$ git add src
$ git commit				# create version(=history) with editor
$ git commit -m "Message"	# create version(=history) with message
$ git commit -am "Message"	# create version(=history) + add(except untracted) with message
$ git log					# show version
$ git log --stat			# show version statics
$ git log -p				# show version with the changes(diffs)
$ git diff					# show change
$ git checkout  [ID]		# change the working tree to a specific version(Checkout is to move HEAD=my version at current branch)
# reset(=delete version) vs. revert(=no delete=new version of before version by one step
$ git reset --hard [ID]		# delete a specific version
$ git revert [ID]			# revert without deleting(=reset) the version
```


## Backup & Restore
> Git를 통해 백업과 복원을 할 수 있다.


## Collaboration
> Git은 협업을 지원한다.


## Tip
- .gitignore 파일은 1개만 그리고 루트에 있어야 한다.
- 처음만 올리고 이후 변경은 무시하고 싶다면
	- 방안 1) assume-unchanged
		```bash
		$ git update-index --assume-unchanged config.json				# 로컬에서만 무시
		$ git update-index --no-assume-unchanged config.json			# 로컬에서만 무시 해제
		```
	- 방안 2) skip-worktree
		```bash
		$ git update-index --skip-worktree config.json					# 워킹 트리에서 무시
		$ update-index --no-skip-worktree config.json					# 워킹 트리에서 무시 해제
		```
	- 방안 3) 설정 파일 템플릿 사용(권장)
		```
		config.example.json	: Git에 저장
		config.json			: .gitignore에 추가
		```