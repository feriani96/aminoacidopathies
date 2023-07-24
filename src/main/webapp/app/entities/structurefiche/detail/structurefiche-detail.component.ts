import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStructurefiche } from '../structurefiche.model';

@Component({
  selector: 'jhi-structurefiche-detail',
  templateUrl: './structurefiche-detail.component.html',
})
export class StructureficheDetailComponent implements OnInit {
  structurefiche: IStructurefiche | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ structurefiche }) => {
      this.structurefiche = structurefiche;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
