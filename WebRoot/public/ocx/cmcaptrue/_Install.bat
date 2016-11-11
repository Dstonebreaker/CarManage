regsvr32 "%~dp0\CmCaptureOcx.ocx"
regsvr32 "%~dp0\vmmjpg.dll"
copy "%~dp0\MJPGDec.ax" C:\Windows\System32
regsvr32 MJPGDec.ax

