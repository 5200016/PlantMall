/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysPlantLogComponent } from 'app/entities/sys-plant-log/sys-plant-log.component';
import { SysPlantLogService } from 'app/entities/sys-plant-log/sys-plant-log.service';
import { SysPlantLog } from 'app/shared/model/sys-plant-log.model';

describe('Component Tests', () => {
    describe('SysPlantLog Management Component', () => {
        let comp: SysPlantLogComponent;
        let fixture: ComponentFixture<SysPlantLogComponent>;
        let service: SysPlantLogService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysPlantLogComponent],
                providers: []
            })
                .overrideTemplate(SysPlantLogComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysPlantLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysPlantLogService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysPlantLog(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysPlantLogs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
