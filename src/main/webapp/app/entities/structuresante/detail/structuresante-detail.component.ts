import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStructuresante } from '../structuresante.model';

@Component({
  selector: 'jhi-structuresante-detail',
  templateUrl: './structuresante-detail.component.html',
})
export class StructuresanteDetailComponent implements OnInit {
  structuresante: IStructuresante | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ structuresante }) => {
      this.structuresante = structuresante;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
