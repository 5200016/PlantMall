/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysPlantLogDetailComponent } from 'app/entities/sys-plant-log/sys-plant-log-detail.component';
import { SysPlantLog } from 'app/shared/model/sys-plant-log.model';

describe('Component Tests', () => {
    describe('SysPlantLog Management Detail Component', () => {
        let comp: SysPlantLogDetailComponent;
        let fixture: ComponentFixture<SysPlantLogDetailComponent>;
        const route = ({ data: of({ sysPlantLog: new SysPlantLog(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysPlantLogDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysPlantLogDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysPlantLogDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysPlantLog).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
