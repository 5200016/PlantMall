/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCollectionDetailComponent } from 'app/entities/sys-collection/sys-collection-detail.component';
import { SysCollection } from 'app/shared/model/sys-collection.model';

describe('Component Tests', () => {
    describe('SysCollection Management Detail Component', () => {
        let comp: SysCollectionDetailComponent;
        let fixture: ComponentFixture<SysCollectionDetailComponent>;
        const route = ({ data: of({ sysCollection: new SysCollection(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCollectionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysCollectionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCollectionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysCollection).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
