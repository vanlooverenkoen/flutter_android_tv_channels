import 'package:flutter/material.dart';
import 'package:android_tv_channels/android_tv_channels.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int? channelId1;
  int? channelId2;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: ListView(
          children: [
            MaterialButton(
              child: const Text('Create recommendations channel'),
              onPressed: () async {
                channelId1 = await AndroidTvChannels.addChannel(name: 'Recommendations');
                print(channelId1);
              },
            ),
            MaterialButton(
              child: const Text('Delete recommendations channel'),
              onPressed: () async {
                final channelId = channelId1;
                if (channelId == null) return;
                await AndroidTvChannels.deleteChannel(channelId: channelId);
                channelId1 = null;
              },
            ),
            MaterialButton(
              child: const Text('Add movie to recommendations channel'),
              onPressed: () {
                final channelId = channelId1;
                if (channelId == null) return;
                AndroidTvChannels.addMovie(
                  channelId: channelId,
                  movie: const MovieData(
                    title: 'Mission Impossible',
                    intentUri: '',
                  ),
                );
              },
            ),
            MaterialButton(
              child: const Text('Create recommendations 2 channel'),
              onPressed: () async {
                channelId2 = await AndroidTvChannels.addChannel(name: 'Recommendations 2', iconResName: 'android');
                print(channelId2);
              },
            ),
            MaterialButton(
              child: const Text('Delete recommendations 2 channel'),
              onPressed: () async {
                final channelId = channelId2;
                if (channelId == null) return;
                await AndroidTvChannels.deleteChannel(channelId: channelId);
                channelId2 = null;
              },
            ),
            MaterialButton(
              child: const Text('Add movie to recommendations 2 channel'),
              onPressed: () {
                final channelId = channelId2;
                if (channelId == null) return;
                AndroidTvChannels.addMovie(
                  channelId: channelId,
                  movie: const MovieData(
                    title: 'Mission Impossible 2',
                    intentUri: '',
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}
